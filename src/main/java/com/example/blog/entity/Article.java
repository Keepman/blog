package com.example.blog.entity;

import com.example.blog.Result.ResultMap;
import lombok.Data;

/**
 * @Author: zoulei
 * @Date: 2019/8/6 10:18
 * @Version 1.0
 */

@Data
public class Article {
    // 文章主键ID
    private Integer Id;
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
}
