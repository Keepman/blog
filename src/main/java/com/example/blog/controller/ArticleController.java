package com.example.blog.controller;

import com.example.blog.Result.ResultMap;
import com.example.blog.annotations.RoleCheck;
import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import com.example.blog.service.ArticleService;
import com.example.blog.utils.AccountUtils;
import com.example.blog.utils.BuildArticleTabloidUtil;
import com.example.blog.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.blog.utils.TimeUtil.getFormatDateForSix;

/**
 * @Author: ymt
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
        Map msg = new HashMap();
        Account account = AccountUtils.getAccount();
        article.setArticleDate(getFormatDateForSix());
        article.setArticleId(System.currentTimeMillis());
        article.setArticleAuthor(account.getUserName());
        // 根据<！--more--> 标签生成内容摘要
        String articleTabloid = BuildArticleTabloidUtil.buildArticleTabloid(article.getArticleTabloid());
        article.setArticleTabloid(articleTabloid);
        Long lastArticle = articleService.lastArticle(article.getArticleId());
        // 每次发布文章 添加上本篇文章上一篇文章的ID和 添加上一篇文章 下一篇的ID
        if (lastArticle == null) {
            article.setLastArticleId(0L);
        } else {
            article.setLastArticleId(lastArticle);
        }
        articleService.updateArticleNextID(article.getArticleId(), lastArticle);
        Integer integer = articleService.insertArticle(article);
        if (integer != 0) {
            map.setStatus("200");
        } else {
            map.setStatus("500");
        }
        return map;
    }

    @RequestMapping("/selectCountArticle")
    public Integer selectCountArticle() {
        return articleService.selectCountArticle();
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    @RequestMapping("/selectAllClassify")
    public List<Classify> selectAllClassify() {
        return articleService.selectAllClassify();
    }

    /**
     * 模糊查询全网文章
     *
     * @param text 模糊查询字段
     */
    @RequestMapping("/selectLikeArticle")
    public List<Article> selectLikeArticle(@RequestParam("text") String text) {
        List<Article> articleList = articleService.selectLikeArticle(text);
        System.out.println("查询到的文章数" + articleList.size());
        return articleList;
    }
}
