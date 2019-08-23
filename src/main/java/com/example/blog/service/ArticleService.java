package com.example.blog.service;

import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ymt
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
    /**
     * 查询热门文章5篇 按照文章浏览量从大到小排序
     */
    List<Article> selectHotArticle();

    List<Article> selectAllArticleByClassify(String articleCategories);

}
