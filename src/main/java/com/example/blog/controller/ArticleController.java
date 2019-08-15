package com.example.blog.controller;

import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import com.example.blog.utils.BuildArticleTabloidUtil;
import com.example.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


import static com.example.blog.utils.TimeUtil.getFormatDateForSix;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 11:23
 * @Version 1.0
 */

@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 发布文章
     */
    @RequestMapping(value = "/PublishArticle")
    public Article PublishArticle(@RequestBody Article article, HttpServletResponse response) {
        Account account = RedisUtil.getAccount();
        article.setArticleDate(getFormatDateForSix());
        article.setArticleId(articleService.selectCountArticle() + 1);
        article.setArticleAuthor(account.getRoleName());
        // 根据<！--more--> 标签生成内容摘要
        String articleTabloid = BuildArticleTabloidUtil.buildArticleTabloid(article.getArticleContent());
        article.setArticleTabloid(articleTabloid);
        articleService.insertArticle(article);
        return article;
    }
}
