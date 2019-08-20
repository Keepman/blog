package com.example.blog.controller;

import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import com.example.blog.utils.AccountUtils;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zoulei
 * @Date: 2019/8/9 13:43
 * @Version 1.0
 */
@Controller
public class BackController {
    private static Logger log = LoggerFactory.getLogger(BackController.class);

    @Autowired
    private ArticleService articleService;
    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Account account = AccountUtils.getAccount();
        model.addAttribute("account", account);
        log.info("跳转到首页");
        return "index";
    }

    /**
     * 跳转注册页
     *
     * @return
     */
    @RequestMapping("/registerPage")
    public String registerPage() {
        log.info("跳转到注册页");
        return "register";
    }


    /**
     * 跳转发表文章页
     */
    @RequestMapping("/editorPage")
    public String editorPage() {
        log.info("跳转到发表文章页");
        return "editor";
    }

    /**
     * 跳转主页
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage(Model model) {
        log.info("跳转到登录页");
        return "login";
    }

    /**
     * 跳转文章显示
     */
    @RequestMapping("/Article/{articleId}")
    public String show(@PathVariable("articleId") long articleId,Model model){
        Article article = articleService.selectByArticleId(articleId);
        model.addAttribute("article",article);
        return null;
    }







    /**
     * 注销账号
     */
    @RequestMapping("/logout")
    public String logout() {
        String onlyNum = CookieUtils.getCookieValue("onlyNum");
        CookieUtils.removeCookie("onlyNum");
        RedisUtil.remove(onlyNum);
        log.info("注销成功");
        // 再重定向到登陆页面
        return "login";
    }
}