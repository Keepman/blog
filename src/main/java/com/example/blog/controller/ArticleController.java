package com.example.blog.controller;

import com.example.blog.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 11:23
 * @Version 1.0
 */

@Controller
@RequestMapping("/Article")
public class ArticleController {

    /**
     * 发布文章
     */
    @RequestMapping(value = "/PublishArticle")
    public String PublishArticle(@RequestBody Article article, HttpServletResponse response) throws IOException {
        System.out.println(article);
        return null;
    }
}
