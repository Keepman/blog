package com.example.blog.service.serverImpl;

import com.example.blog.entity.Article;
import com.example.blog.service.PersonalCenterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/22 15:20
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {
    @Override
    public Integer selectArticleNumByAccount() {
        return null;
    }

    @Override
    public Integer selectMessageNumByAccount() {
        return null;
    }

    @Override
    public Integer selectArticleStarNumByAccount() {
        return null;
    }

    @Override
    public List<Article> selectSoonArticle() {
        return null;
    }
}