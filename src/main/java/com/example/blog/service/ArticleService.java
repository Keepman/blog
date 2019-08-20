package com.example.blog.service;

import com.example.blog.entity.Article;

/**
 * @Author mayn
 * @Date 2019/8/14 9:12
 */
public interface ArticleService {
    Integer insertArticle(Article article);

    Integer selectCountArticle();

    Article selectByArticleId(long articleId);

    // 上一篇文章
    Long lastArticle(Long articleId);

    // 下一篇文章
    Long nextArticle();

    // 更新上一篇文章的nextID
    void updateArticleNextID(Long nextArticleId, Long articleId);
}
