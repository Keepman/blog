package com.example.blog.service;

import com.example.blog.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ymt
 * @Date: 2019/8/8 8:23
 * @Version 1.0
 */
public interface PageListService {
    Page<Article> selectArticle();
    Page<Article> selectArticleByAuthor(String articleAuthor);

}
