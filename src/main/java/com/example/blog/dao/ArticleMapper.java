package com.example.blog.dao;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author mayn
 * @Date 2019/8/14 9:10
 */

@Mapper
public interface ArticleMapper {
    Integer insertArticle(Article article);

    Integer selectCountArticle();

    Article selectByArticleId(long articleId);

    // 上一篇文章
    Long lastArticle(Long articleId);

    // 下一篇文章
    Long nextArticle();

    void updateArticleNextID(@Param("nextArticleId") Long nextArticleId, @Param("articleId") Long articleId);
}
