package com.example.blog;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Article;
import com.example.blog.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    private MailService mailService;

    @Test
    public void currtime() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void json(){
        Article article = new Article();
        article.setArticleContent("今天天气真好");
        article.setArticleId(1L);
        String s = JSON.toJSONString(article);
        System.out.println(s);
    }

    @Test
    public void testEmail(){
        mailService.sendSimpleMail("1017020609@qq.com");
    }

}
