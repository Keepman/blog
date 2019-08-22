package com.example.blog.service;

import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;

import java.util.List;

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

    // 文章所有分类查询
    List<Classify> selectAllClassify();
    // 模糊查询全网文章
    List<Article> selectLikeArticle(String text);

}
