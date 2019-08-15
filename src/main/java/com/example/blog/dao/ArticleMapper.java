package com.example.blog.dao;

import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author mayn
 * @Date 2019/8/14 9:10
 */

@Mapper
public interface ArticleMapper {
    Integer insertArticle(Article article);

    Integer selectCountArticle();
}
