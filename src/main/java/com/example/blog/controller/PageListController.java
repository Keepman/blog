package com.example.blog.controller;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Article;
import com.example.blog.service.PageList;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zoulei
 * @Date: 2019/8/8 8:20
 * @Version 1.0
 */

@RestController
public class PageListController {

    @Autowired
    PageList pageList;

    /**
     * 分页查询文章
     * @param pageNum 当前页码
     * @param pageSize 想要查询的信息条数
     * @return Page<Article> 将返回的实体类放入Page<>中返回
     */
    @RequestMapping(value = "/selectArticle")
    public Page<Article> selectArticle(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles = pageList.selectArticle();
        return articles;
    }
}

