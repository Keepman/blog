package com.example.blog.service.serverImpl;

import com.example.blog.dao.PageListMapper;
import com.example.blog.entity.Article;
import com.example.blog.service.PageList;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 8:29
 * @Version 1.0
 */

@Service
public class PageListImpl implements PageList {

    @Autowired
    private PageListMapper pageListMapper;

    @Override
    public Page<Article> selectArticle() {
        return pageListMapper.selectArticle();
    }
}
