package com.example.blog;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

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

}
