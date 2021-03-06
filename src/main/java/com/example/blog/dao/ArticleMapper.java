package com.example.blog.dao;

import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ymt
 * @Date 2019/8/14 9:10
 */

@Mapper
public interface ArticleMapper {
    Integer insertArticle(Article article);

    Integer selectCountArticle();

    Article selectByArticleId(long articleId);

    /**
     * 上一篇文章
     *
     * @return
     */
    Long lastArticle(Long articleId);

    /**
     * 下一篇文章
     *
     * @return
     */
    Long nextArticle();

    /**
     * 更新上一篇文章的NextID
     *
     * @return
     */
    void updateArticleNextID(@Param("nextArticleId") Long nextArticleId, @Param("articleId") Long articleId);

    /**
     * 查询出所有分类
     *
     * @return
     */
    List<Classify> selectAllClassify();

    /**
     * 根据分类查询出所有文章
     * @param articleCategories 文章分类
     * @return
     */
    List<Article> selectAllArticleByClassify(@Param("articleCategories") String articleCategories);

    /**
     * 模糊查询全网文章
     * @param text 用户输入的需要模糊查询的内容
     */
    List<Article> selectLikeArticle(@Param("text") String text);

    /**
     * 查询热门文章5篇 按照文章浏览量从大到小排序
     */
    List<Article> selectHotArticle();
}
