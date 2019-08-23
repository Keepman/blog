package com.example.blog.service.serverImpl;

import com.example.blog.dao.ArticleMapper;
import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ymt
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
    public Long lastArticle(Long articleId) {
        return articleMapper.lastArticle(articleId);
    }

    @Override
    public Long nextArticle() {
        return articleMapper.nextArticle();
    }

    @Override
    public void updateArticleNextID(Long nextArticleId, Long articleId) {
        articleMapper.updateArticleNextID(nextArticleId, articleId);
    }

    @Override
    public List<Article> selectLikeArticle(String text) {
        return articleMapper.selectLikeArticle(text);
    }

    @Override
    public List<Article> selectAllArticleByClassify(String articleCategories) {
        return articleMapper.selectAllArticleByClassify(articleCategories);
    }

    @Override
    public List<Article> selectHotArticle() {
        return articleMapper.selectHotArticle();
    }

    @Override
    public List<Classify> selectAllClassify() {
        return articleMapper.selectAllClassify();
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
