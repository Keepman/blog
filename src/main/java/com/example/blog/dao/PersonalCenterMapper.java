package com.example.blog.dao;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/22 15:21
 */
@Mapper
public interface PersonalCenterMapper {
    /**
     * 查询该用户总共发表文章数量
     */
    Integer selectArticleNumByAccount(@Param("articleAuthor") String articleAuthor);

    /**
     * 查询该用户总共评论量
     */
    Integer selectMessageNumByAccount(@Param("articleAuthorId") Integer articleAuthorId);

    /**
     * 查询该用户收获的总点赞次数
     */
    Integer selectArticleStarNumByAccount(@Param("articleAuthor") String articleAuthor);

    /**
     * 最近发布的文章 3篇
     */
    List<Article> selectSoonArticle();

}
