package com.example.blog.service.serverImpl;

import com.example.blog.dao.PersonalCenterMapper;
import com.example.blog.entity.Article;
import com.example.blog.service.PersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/22 15:20
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Autowired
    private PersonalCenterMapper personalCenterMapper;

    @Override
    public Integer selectArticleNumByAccount(String articleAuthor) {
        return personalCenterMapper.selectArticleNumByAccount(articleAuthor);
    }

    @Override
    public Integer selectMessageNumByAccount(Integer articleAuthorId) {
        return personalCenterMapper.selectMessageNumByAccount(articleAuthorId);
    }

    @Override
    public Integer selectArticleStarNumByAccount(String articleAuthor) {
        return personalCenterMapper.selectArticleStarNumByAccount(articleAuthor);
    }

    @Override
    public List<Article> selectSoonArticle() {
        return null;
    }
}
