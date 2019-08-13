package com.example.blog.controller;

import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zoulei
 * @Date: 2019/8/9 13:43
 * @Version 1.0
 */
@Controller
public class BackController {
    private static Logger log = LoggerFactory.getLogger(BackController.class);

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping("/")
    public String login() {
        log.info("跳转到首页");
        return "login";
    }
    /**
     * 跳转注册页
     *
     * @return
     */
    @RequestMapping("/registerPage")
    public String registerPage() {
        log.info("跳转到注册页");
        return "register";
    }


    /**
     * 跳转发表文章页
     */

    @RequestMapping("/editorPage")
    public String editorPage() {
        log.info("跳转到发表文章页");
        return "editor";
    }

    /**
     * 跳转主页
     *
     * @return
     */
    @RequestMapping("/indexPage")
    public String indexPage() {
        log.info("跳转到主页");
        return "index";
    }

    /**
     * 注销账号
     */
    @RequestMapping("/logout")
    public String logout() {
        String onlyNum = CookieUtils.getCookieValue("onlyNum");
        CookieUtils.removeCookie("onlyNum");
        RedisUtil.remove(onlyNum);
        log.info("注销成功");
        // 再重定向到登陆页面
        return "login";
    }
}