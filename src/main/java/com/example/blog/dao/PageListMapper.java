package com.example.blog.dao;

import com.example.blog.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ymt
 * @Date: 2019/8/8 8:33
 * @Version 1.0
 */

@Mapper
public interface PageListMapper {
    Page<Article> selectArticle();
    Page<Article> selectArticleByAuthor(@Param("articleAuthor") String articleAuthor);
}
