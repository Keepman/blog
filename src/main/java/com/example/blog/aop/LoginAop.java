package com.example.blog.aop;

import com.example.blog.utils.CookieUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: zoulei
 * @Date: 2019/8/12 15:25
 * @Version 1.0
 */
@Component
@Aspect
public class LoginAop {
    private static Logger log = LoggerFactory.getLogger(LoginAop.class);

    @Pointcut("@annotation(com.example.blog.annotations.LoginCheck)")
    public void pointcut() {
    }

    ;

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String onlyNum = CookieUtils.getCookieValue("onlyNum");
        if (!"".equals(onlyNum) && onlyNum != null) {
            // 处理内容
            System.out.println("处理");
            String retStr = (String) pjp.proceed();
            return retStr;
        } else {
            System.out.println("跳转页面");
        }
        return null;
    }


}
