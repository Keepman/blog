package com.example.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.blog.entity.Account;
import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.HttpUtils;
import com.example.blog.utils.RedisUtil;
import com.example.blog.utils.SubStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author mayn
 * @Date 2019/9/11 16:55
 * 第三方登录
 */
@RestController
@RequestMapping("/thirdPartyLogin")
public class ThirdPartyLoginsController {
    private static Logger log = LoggerFactory.getLogger(ThirdPartyLoginsController.class);

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;


    /**
     * github获取AccessToken
     * 返回accessToken
     */
    @RequestMapping("/githubGetAccessToken")
    public String githubGetAccessToken(@RequestParam("code") String code) {
        try {
            Map<String, String> param = new HashMap<>(30);
            param.put("client_id", clientId);
            param.put("client_secret", clientSecret);
            param.put("code", code);
            String tokenResponse = HttpUtils.httpclientPost("https://github.com/login/oauth/access_token", param, "utf-8");
            String accessToken = SubStringUtils.subString(tokenResponse, "access_token=", "&scope");
            log.info("accessToken为" + accessToken);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从github中获取个人信息
     */
    @RequestMapping("/githubGetUser")
    public Integer githubGetUser(@RequestParam("token") String token) {
        String url = "https://api.github.com/user?access_token=" + token;
        String tokenResponse = HttpUtils.get(url, "utf-8");
        JSONObject tokenResponseJson = JSONObject.parseObject(tokenResponse);
        Account account = new Account();
        Integer id = (Integer) tokenResponseJson.get("id");
        String userName = (String) tokenResponseJson.get("login");
        // 获得创建时间
        String userDate = (String) tokenResponseJson.get("created_at");
        if (id != null && !StringUtils.isBlank(userName)) {
            account.setUserId(id);
            account.setUserName(userName);
            //account.setUserDate();
            String onlyNum = UUID.randomUUID().toString().replaceAll("-", "");
            // 设置cookie，key为onlyNum，值为一个随机生成数
            CookieUtils.setCookie("onlyNum", onlyNum, 86400);
            RedisUtil.set(onlyNum, JSON.toJSONString(account), 86400L);
            return 200;
        }else {
            log.error("github中无此账号");
            return 999;
        }


    }
}
