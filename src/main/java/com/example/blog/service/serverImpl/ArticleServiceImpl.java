package com.example.blog.service.serverImpl;

import com.example.blog.dao.ArticleMapper;
import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author mayn
 * @Date 2019/8/14 9:12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;


    @Override
    public Article selectByArticleId(long articleId) {
        return articleMapper.selectByArticleId(articleId);
    }

    @Override
    public Integer insertArticle(Article article) {
        Integer num = articleMapper.insertArticle(article);
        return num;
    }

    @Override
    public Integer selectCountArticle() {
        Integer ArticleCount = articleMapper.selectCountArticle();
        return ArticleCount;
    }
}
