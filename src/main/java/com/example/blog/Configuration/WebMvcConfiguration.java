//package com.example.blog.Configuration;
//
//import com.example.blog.Interceptors.LoginHandlerInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//
///**
// * @author mayn
// */
//@Configuration
//public class WebMvcConfiguration implements WebMvcConfigurer {
//
//    @Autowired
//    LoginHandlerInterceptor loginHandlerInterceptor;
//
//    /**
//     * 注册拦截器
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截所有的请求
//        InterceptorRegistration interceptor = registry.addInterceptor(loginHandlerInterceptor);
//        interceptor.addPathPatterns("/**");
//        interceptor.excludePathPatterns("/", "/code", "/isRepeat", "/login", "/register","/error");
//        // 静态资源
//        interceptor.excludePathPatterns("/static/**");
//    }
//}
