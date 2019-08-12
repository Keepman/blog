package com.example.blog.controller;

import com.example.blog.annotations.LoginCheck;
import com.example.blog.entity.Article;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import com.example.blog.utils.SpringHttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 11:23
 * @Version 1.0
 */

@RestController
@RequestMapping("/Article")
public class ArticleController {

    /**
     * 发布文章
     */
    @LoginCheck
    @RequestMapping(value = "/PublishArticle")
    public void PublishArticle(@RequestBody Article article, HttpServletResponse response) throws IOException {
        // 判断是否登录
//        String onlyNum = CookieUtils.getCookieValue("onlyNum");
//        if (!"".equals(onlyNum) && onlyNum != null) {
//
//            // 处理内容
//            System.out.println("处理");
//
//        } else {
//            System.out.println("跳转页面");
//        }
    }

}
