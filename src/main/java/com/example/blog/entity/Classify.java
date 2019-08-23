package com.example.blog.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author ymt
 * @Date 2019/8/22 10:15
 */
@Data
public class Classify {
    private int id;
    // 分类
    private String classify;
    // 改分类的文章列表
    private List<Article> articleList;
}
