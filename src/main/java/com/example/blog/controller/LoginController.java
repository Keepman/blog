package com.example.blog.controller;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Account;
import com.example.blog.service.LoginService;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.MD5Util;
import com.example.blog.utils.RedisUtil;
import com.example.blog.utils.VerifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@RestController
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    /**
     * 注册
     *
     * @param account
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public void register(@RequestBody Account account) {
        boolean repeat = loginService.isRepeat(account.getUserAdmin());
        if (repeat) {
            // 用户不重复则创建账号
            loginService.register(account);
        }
    }

    /**
     * 检查用户名是否重复
     *
     * @param
     */
    @RequestMapping(value = "/isRepeat")
    public void isRepeat(@RequestParam("username") String username) {
        loginService.isRepeat(username);
    }

    /**
     * 用户每次登录的时候都会生成一个唯一的表示token，用它来作为key，用户信息作为value，然后将token存到Cookie里面返给浏览器。用户下次
     * 访问用户中心的时候，从Cookie里面取token，再用token从redis中取用户信息，来判断是否允许访问用户中心。
     *
     * @param response
     * @param request
     * @param map      前端传入的参数 将它封装成map
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Integer login(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, String> map) throws IOException {
        // 从cookie取key为onlyNum值，如不存在，则读取输入账号密码，如果存在，取出改值将它设为redis的key，从而取出redis中存储的账号信息
        String getOnlyNum = CookieUtils.getCookieValue("onlyNum");
        if (!StringUtils.isBlank(getOnlyNum)) {
            String accountMsgForJson = RedisUtil.get(getOnlyNum);
            if (!StringUtils.isBlank(accountMsgForJson)) {
                // 将redis中取出的账号信息(当前为JSON)转换为Account对象
                Account accountMsg = JSON.parseObject(accountMsgForJson, Account.class);
                Account account = loginService.login(accountMsg.getUserAdmin(), accountMsg.getUserPsw());
                // 前端ajax接收信息为1时，跳转页面至index.html，信息为0时，页面刷新不跳转
                if (account != null) {
                    log.info("redis存储的账号或密码正确-----登陆成功");
                    return 1;
                } else {
                    log.error("redis存储的账号或密码错误-----登陆失败");
                    return 0;
                }
                // cookie中key为onlyNum对应的value，该value对应redis中的key，无法取出对应的redis值时
            } else {
                log.error("redis并无key为：" + getOnlyNum);
                CookieUtils.removeCookie("onlyNum");
                log.info("cookie删除key为" + getOnlyNum + "成功");
                return 0;
            }
            // 如果cookie中没有key为onlyNum（说明从未登录，或者登陆过后已登出）
        } else {
            String uuid = CookieUtils.getCookieValue("UUID");
            if (!StringUtils.isBlank(uuid)) {
                // 从cookie中取出key为UUID的值，将该值当做redis的key，能够取出真实的验证码
                String yzm = RedisUtil.get(uuid);
                if (!StringUtils.isBlank(yzm)) {
                    // 获取前端页面传入进来的yzm
                    String getYZM = map.get("yzm");
                    // 如果前端传入的验证码值和redis中取出的验证码匹配
                    if (getYZM.equals(yzm)) {
                        String username = map.get("username");
                        String password = map.get("password");
                        // 将密码MD5加密之后
                        String passwordMd5 = MD5Util.md5Encrpt(password);
                        Account account = loginService.login(username, passwordMd5);
                        if (account != null) {
                            // 登录成功从redis中删除key，再次登录需要重新获取验证码
                            RedisUtil.remove(uuid);
                            String onlyNum = UUID.randomUUID().toString().replaceAll("-", "");
                            // 设置cookie，key为onlyNum，值为一个随机生成数
                            CookieUtils.setCookie("onlyNum", onlyNum);
                            // 设置redis，将key为cookie的key，一个随机生成数，值为账号对象，有效期为1天
                            RedisUtil.set(onlyNum, JSON.toJSONString(account), 86400L);
                            return 1;
                        } else {
                            log.error("输入的账号或者密码错误");
                            return 0;
                        }
                    } else {
                        log.error("验证码输入错误");
                        return 0;
                    }
                } else {
                    log.error("redis中存储验证码的key为空，请刷新验证码");
                    return 0;
                }
            } else {
                log.error("cookie中UUID为空，请刷新验证码");
                return 0;
            }
        }
    }

    /**
     * 获取验证码
     *
     * @param response
     */
    @RequestMapping("/code")
    public void code(HttpServletResponse response, HttpServletRequest request) {
        Object[] objects = VerifyUtil.createImage();
        BufferedImage image = (BufferedImage) objects[1];
        // 设置浏览器不缓存本页
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Expires", "0");
        response.setContentType("image/jpeg");
        log.info("验证码为"+objects[0]);
        // 获取验证码
        String yzm = (String) objects[0];
        // 获取唯一数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("验证码uuid为"+uuid);
        // 将唯一数作为值，key为UUID存入cookie
        CookieUtils.setCookie("UUID", uuid);
        // 将唯一数作为key，生成的验证码的值作为value，存入redis，有效期为3分钟
        RedisUtil.set(uuid, yzm, 180L);
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
