package com.example.blog.controller;

import com.example.blog.Result.ResultMap;
import com.example.blog.annotations.RoleCheck;
import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import com.example.blog.utils.AccountUtils;
import com.example.blog.utils.BuildArticleTabloidUtil;
import com.example.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

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
    @RoleCheck
    @RequestMapping(value = "/PublishArticle")
    public ResultMap PublishArticle(@RequestBody Article article, HttpServletResponse response) {
        ResultMap map = new ResultMap();
        Account account = AccountUtils.getAccount();
        article.setArticleDate(getFormatDateForSix());
        articleService.selectCountArticle();
        article.setArticleId(System.currentTimeMillis());
        article.setArticleAuthor(account.getUserName());
        // 根据<！--more--> 标签生成内容摘要
        String articleTabloid = BuildArticleTabloidUtil.buildArticleTabloid(article.getArticleContent());
        article.setArticleTabloid(articleTabloid);
        Integer integer = articleService.insertArticle(article);
        if (integer != 0) {
            map.setMessage("新增文章成功");
            map.setStatus("200");
        } else {
            map.setMessage("新增文章失败");
            map.setStatus("500");
        }
        return map;
    }
}
