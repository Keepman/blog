package com.example.blog.dao;

import com.example.blog.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 8:33
 * @Version 1.0
 */

@Mapper
public interface PageListMapper {
    Page<Article> selectArticle();

}
