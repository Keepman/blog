package com.example.blog.controller;

import com.example.blog.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author mayn
 * @Date 2019/9/11 16:55
 * 第三方登录
 */
@Controller
public class ThirdPartyLoginsController {


    /**
     * github登录
     */
    // https://github.com/login/oauth/authorize?client_id=2221efedd9edfe0aac24&redirect_uri=http://localhost:8888/index.html&state=11
    @RequestMapping("/githubLogin")
    public void githubLogin(HttpServletResponse response){
        try {
            // 返回code值
            response.sendRedirect("https://github.com/login/oauth/authorize?client_id=2221efedd9edfe0aac24&redirect_uri=http://localhost:8888/index.html&state=11");
            // https://github.com/login/oauth/access_token?client_id=xxx&client_secret=xxx&code=xxx&redirect_uri=回调地址
            HttpUtils.httpclientPost("https://github.com/login/oauth/access_token?client_id=2221efedd9edfe0aac24&client_secret=0ce77dbb800118392462cd4ab8ce11208f13894c&code=xxx&redirect_uri=http://localhost:8888/index.html",null,"utf-8");
            // 这次会得到响应的access_token
            // https://api.github.com/user?access_token=xxx
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
