package com.example.blog.dao;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

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
    Integer selectArticleNumByAccount();

    /**
     * 查询该用户总共评论量
     */
    Integer selectMessageNumByAccount();

    /**
     * 查询该用户为他人点赞次数
     */
    Integer selectArticleStarNumByAccount();

    /**
     * 最近发布的文章 3篇
     */
    List<Article> selectSoonArticle();

}
