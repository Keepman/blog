package com.example.blog.controller;

import com.example.blog.entity.Account;
import com.example.blog.entity.Article;
import com.example.blog.entity.Classify;
import com.example.blog.entity.Message;
import com.example.blog.service.ArticleService;
import com.example.blog.service.LeaveMessageService;
import com.example.blog.service.PageListService;
import com.example.blog.service.PersonalCenterService;
import com.example.blog.utils.AccountUtils;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private PageListService pageListService;

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
        for (Article article : hotArticleList) {
            Integer msgCount = leaveMessageService.selectCountMessageByArticleId(article.getArticleId());
            article.setArticleLeaveMessage(msgCount);
        }
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
        try {
            Account account = AccountUtils.getAccount();
            model.addAttribute("account", account);
        } catch (Exception e) {
            model.addAttribute("account", new Account());
            log.info("账户未登录");
        } finally {
            Article article = articleService.selectByArticleId(articleId);
            List<Message> msgList = leaveMessageService.selectMessageByArticleId(articleId);
            Integer msgCount = leaveMessageService.selectCountMessageByArticleId(articleId);
            model.addAttribute("article", article);
            model.addAttribute("msgList", msgList);
            model.addAttribute("msgCount", msgCount);
            log.info("跳转文章显示");
            return "articlePage";
        }
    }

    /**
     * 跳转个人中心
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(Model model, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Account account = AccountUtils.getAccount();
        Integer articleNum = personalCenterService.selectArticleNumByAccount(account.getUserId());
        Integer articleStar = personalCenterService.selectArticleStarNumByAccount(account.getUserId());
        Integer messageNum = personalCenterService.selectMessageNumByAccount(account.getUserId());
        if (pageNum == null) {
            PageHelper.startPage(1, 3);
        } else {
            PageHelper.startPage(pageNum, 3);
        }
        Page<Article> articlePage = pageListService.selectArticleByAuthor(account.getUserId());
        for (Article article : articlePage) {
            Integer msgCount = leaveMessageService.selectCountMessageByArticleId(article.getArticleId());
            article.setArticleLeaveMessage(msgCount);
        }
        model.addAttribute("account", account);
        model.addAttribute("ArticleNum", articleNum);
        model.addAttribute("ArticleStar", articleStar);
        model.addAttribute("MessageNum", messageNum);
        model.addAttribute("articlePage", articlePage);
        log.info("跳转个人中心");
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

    /**
     * 分类页面
     */
    @RequestMapping("/classify")
    public String classify(Model model) {
        List<Classify> classifyList = articleService.selectAllClassify();
        for (Classify classify : classifyList) {
            List<Article> articleList = articleService.selectAllArticleByClassify(classify.getClassify());
            classify.setArticleList(articleList);
        }
        model.addAttribute("classifyList", classifyList);
        log.info("跳转分类页面");
        // 再重定向到登陆页面
        return "classify";
    }
}