package com.example.blog.aop;

import com.example.blog.Result.ResultMap;
import com.example.blog.entity.Account;
import com.example.blog.utils.AccountUtils;
import com.example.blog.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mayn
 * @Date 2019/8/19 10:17
 */
@Component
@Aspect
public class RoleCheckAop {
    private static Logger log = LoggerFactory.getLogger(RoleCheckAop.class);

    @Pointcut("@annotation(com.example.blog.annotations.RoleCheck)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Account account = AccountUtils.getAccount();
        ResultMap map = new ResultMap();
        if ("ROLE_USER".equals(account.getUserRole()) || "ROLE_ADMIN".equals(account.getUserRole())) {
            map.setStatus("200");
            log.info("您有访问权限");
            pjp.proceed();
        } else {
            map.setStatus("500");
            log.error("您没有该访问权限");
        }
        return map;
    }
}
