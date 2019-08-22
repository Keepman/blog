package com.example.blog.utils;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.blog.utils.RedisUtil.get;

/**
 * @Author ymt
 * @Date 2019/8/19 10:30
 */
public class AccountUtils {
    private static Logger log = LoggerFactory.getLogger(AccountUtils.class);

    public static Account getAccount() {
        String getOnlyNum = CookieUtils.getCookieValue("onlyNum");
        if (!StringUtils.isBlank(getOnlyNum)) {
            String accountMsgForJson = get(getOnlyNum);
            if (!StringUtils.isBlank(accountMsgForJson)) {
                Account accountMsg = JSON.parseObject(accountMsgForJson, Account.class);
                return accountMsg;
            } else {
                log.error("redis记录为空，请重新登录");
            }

        } else {
            log.error("cookie记录为空，请重新登录");
        }
        return null;
    }
}
