package com.example.blog.Interceptors;

import com.example.blog.utils.CookieUtils;
import com.example.blog.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截器
 *
 * @Author: ymt
 * @Date: 2019/8/13 10:00
 * @Version 1.0
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        log.info("请求路径：{}", request.getRequestURI());
        String onlyNum = CookieUtils.getCookieValue("onlyNum");
        if (!StringUtils.isBlank(onlyNum)) {
            String redisNum = RedisUtil.get(onlyNum);
            if (!StringUtils.isBlank(redisNum)) {
                log.info("已登录，能够访问");
                return true;
            } else {
                log.info("redis中无记录，跳转至登录页面");
                log.error("请重新登录");
                // Ajax只是利用脚本访问对应url获取数据而已，不能做除了获取返回数据以外的其它动作了。所以浏览器端是不会发起重定向的。 
                //1）正常的http url请求，只有浏览器和服务器两个参与者。浏览器端发起一个http请求，服务器端处理后发起一个重定向，浏览器端从response中获取到重定向地址，发起另一个http url请求。也就是说，浏览器会按照response中的内容进行响应（如重定向），这是浏览器的功能决定的就得响应。 
                //2）Ajax请求，参与者就有三个即ajax、客户端、服务器，ajax处于客户端和服务器两者之间。过程是客户端发起一个ajax请求，服务器端处理后，如果发起一个重定向，然后ajax会怎么办呢？它只会获取刚才请求返回的数据，其他的任何动作一概不去做，ajax是这么做的（ajax功能就是这么设定的，ajax框架源代码也是这么写的）。 
                //也就是说，引入了ajax之后，ajax就插在浏览器和服务器之间了，服务器给浏览器的response被ajax拦截了，但是ajax本身却什么都不做，也不转达。
                CookieUtils.removeCookie("onlyNum");
                response.sendRedirect("/loginPage");
                return false;
            }
        } else {
            log.info("cookie中无记录，跳转至登录页面");
            log.error("请重新登录");
            response.sendRedirect("/loginPage");
            return false;
        }
    }
}
