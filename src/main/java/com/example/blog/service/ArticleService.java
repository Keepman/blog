package com.example.blog.service;

import com.example.blog.entity.Article;

/**
 * @Author mayn
 * @Date 2019/8/14 9:12
 */
public interface ArticleService {
    void insertArticle(Article article);

    Integer selectCountArticle();
}
