package com.example.blog.service;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/22 15:20
 */
public interface PersonalCenterService {
    /**
     * 查询该用户总共发表文章数量
     */
    Integer selectArticleNumByAccount(String articleAuthor);

    /**
     * 查询该用户总共评论量
     */
    Integer selectMessageNumByAccount(Integer articleAuthorId);

    /**
     * 查询该用户收获的总点赞次数
     */
    Integer selectArticleStarNumByAccount(String articleAuthor);

    /**
     * 最近发布的文章 3篇
     */
    List<Article> selectSoonArticle();
}
