package com.example.blog.Configuration;

import com.example.blog.Interceptors.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author ymt
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的请求
        InterceptorRegistration interceptor = registry.addInterceptor(loginHandlerInterceptor);
        interceptor.addPathPatterns("/**");
        // 主页
        interceptor.excludePathPatterns("/");
        // 验证码
        interceptor.excludePathPatterns("/code");
        // 账号是否重复
        interceptor.excludePathPatterns("/isRepeat");
        // 登录页面
        interceptor.excludePathPatterns("/loginPage");
        // 登录逻辑
        interceptor.excludePathPatterns("/login");
        // 注册
        interceptor.excludePathPatterns("/register");
        // 注册页面
        interceptor.excludePathPatterns("/registerPage");
        // 第三方登录
        interceptor.excludePathPatterns("/thirdPartyLogin/**");
        // 主页面分页查询文章
        interceptor.excludePathPatterns("/selectArticle");
        // 查询文章总数
        interceptor.excludePathPatterns("/Article/selectCountArticle");
        // github登录
        interceptor.excludePathPatterns("/githubLogin");
        // 分类页面
        interceptor.excludePathPatterns("/classify");
        // 发送邮箱验证码
        interceptor.excludePathPatterns("/sendEmail");
        // 第三方登录成功
        interceptor.excludePathPatterns("/loginSuccess");
        // 文章显示页面
        interceptor.excludePathPatterns("/Article/{articleId}");
        // 静态资源
        interceptor.excludePathPatterns("/static/**");
    }
}
