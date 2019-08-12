package com.example.blog.controller;

import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zoulei
 * @Date: 2019/8/9 13:43
 * @Version 1.0
 */
@Controller
public class backController {

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping("/")
    public String login() {
        return "login";
    }


    /**
     * 跳转发表文章页
     */

    @RequestMapping("/editor")
    public String editor() {
        return "editor";
    }

    /**
     * 注销账号
     */
    @RequestMapping("/logout")
    public String logout(){
        String onlyNum = CookieUtils.getCookieValue("onlyNum");
        CookieUtils.removeCookie("onlyNum");
        RedisUtil.remove(onlyNum);
        // 再重定向到登陆页面
        return "login";
    }
}