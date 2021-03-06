package com.example.blog.entity;

import lombok.Data;

/**
 * @Author: ymt
 * @Date: 2019/8/6 10:18
 * @Version 1.0
 */

@Data
public class Article {
    // 文章主键ID
    private int Id;
    // 文章ID
    private Long articleId;
    // 文章正文
    private String articleContent;
    // 文章标题
    private String articleTitle;
    // 文章作者
    private String articleAuthor;
    //创作日期
    private String articleDate;
    // 文章摘要
    private String articleTabloid;
    // 文章点赞数
    private int articleStar;
    // 文章留言数
    private int articleLeaveMessage;
    // 文章分类
    private String articleCategories;
    // 上一篇文章ID
    private Long lastArticleId;
    // 下一篇文章ID
    private Long nextArticleId;
    // 文章浏览量
    private Long articlePageView;
}
