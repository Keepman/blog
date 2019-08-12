package com.example.blog.controller;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Account;
import com.example.blog.service.LoginService;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.MD5Util;
import com.example.blog.utils.RedisUtil;
import com.example.blog.utils.VerifyUtil;
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
        boolean repeat = loginService.isRepeat(account.getRoleAdmin());
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, String> map) {
        String getOnlyNum = CookieUtils.getCookieValue("onlyNum");
        if (getOnlyNum != null && !"".equals(getOnlyNum)) {
            String message = RedisUtil.get(getOnlyNum);
            Account account = JSON.parseObject(message, Account.class);
            loginService.login(account.getRoleAdmin(), account.getRolePsw());
        } else {
            // 获取cookie值
            String uuid = CookieUtils.getCookieValue("UUID");
            // 根据cookie值获取验证码
            String yzm = RedisUtil.get(uuid);
            System.err.println(yzm);
            String getyzm = map.get("yzm");
            if (getyzm.equals(yzm)) {
                String username = map.get("username");
                String password = map.get("password");
                String passwordMd5 = MD5Util.md5Encrpt(password);
                Account account = loginService.login(username, passwordMd5);
                // 登录成功从redis中删除key，再次登录需要重新获取验证码
                RedisUtil.remove(uuid);
                String onlyNum = UUID.randomUUID().toString().replaceAll("-", "");
                CookieUtils.setCookie("onlyNum", onlyNum);
                // 用户每次登录的时候都会生成一个唯一的表示token，用它来作为key，用户信息作为value，然后将token存到Cookie里面返给浏览器。用户下次
                //访问用户中心的时候，从Cookie里面取token，再用token从redis中取用户信息，来判断是否允许访问用户中心。
                RedisUtil.set(onlyNum, JSON.toJSONString(account), 1440L);
            } else {
                log.error("验证码错误");
            }
        }
  }

    /**
     * 获取验证码
     *
     * @param response
     */
    @RequestMapping("/yanzheng")
    public void yanzheng(HttpServletResponse response, HttpServletRequest request) {
        Object[] objects = VerifyUtil.createImage();
        BufferedImage image = (BufferedImage) objects[1];
        // 设置浏览器不缓存本页
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Expires", "0");
        response.setContentType("image/jpeg");
        System.err.println(objects[0]);
        // 获取验证码
        String yzm = (String) objects[0];
        // UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.err.println(uuid);
        // 将UUID为name，UUID值为value存入
        // Cookie cookie = new Cookie("UUID", uuid);
        CookieUtils.setCookie("UUID", uuid);
        // response.addCookie(cookie);
        RedisUtil.set(uuid, yzm, 1000L);
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
