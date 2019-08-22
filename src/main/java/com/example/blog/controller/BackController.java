package com.example.blog.controller;

import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import com.example.blog.entity.Message;
import com.example.blog.service.ArticleService;
import com.example.blog.service.LeaveMessageService;
import com.example.blog.service.PersonalCenterService;
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

import java.util.List;

/**
 * @Author: ymt
 * @Date: 2019/8/9 13:43
 * @Version 1.0
 */
@Controller
public class BackController {
    private static Logger log = LoggerFactory.getLogger(BackController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private LeaveMessageService leaveMessageService;
    @Autowired
    private PersonalCenterService personalCenterService;

    /**
     * 跳转主页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Account account = AccountUtils.getAccount();
        List<Classify> classifyList = articleService.selectAllClassify();
        List<Article> hotArticleList = articleService.selectHotArticle();
        model.addAttribute("account", account);
        model.addAttribute("hotArticleList", hotArticleList);
        model.addAttribute("classifyList", classifyList);
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
     * 跳转登录页
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
    public String show(@PathVariable("articleId") long articleId, Model model) {
        Article article = articleService.selectByArticleId(articleId);
        List<Message> msgList = leaveMessageService.selectMessageByArticleId(articleId);
        Integer msgCount = leaveMessageService.selectCountMessageByArticleId(articleId);
        model.addAttribute("article", article);
        model.addAttribute("msgList", msgList);
        model.addAttribute("msgCount", msgCount);
        return "articlePage";
    }

    /**
     * 跳转个人中心
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(Model model) {
        Account account = AccountUtils.getAccount();
        Integer ArticleNum = personalCenterService.selectArticleNumByAccount(account.getUserName());
        Integer ArticleStar = personalCenterService.selectArticleStarNumByAccount(account.getUserName());
        Integer MessageNum = personalCenterService.selectMessageNumByAccount(account.getUserId());
        model.addAttribute("account", account);
        model.addAttribute("ArticleNum", ArticleNum);
        model.addAttribute("ArticleStar", ArticleStar);
        model.addAttribute("MessageNum", MessageNum);
        return "personalCenter";
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