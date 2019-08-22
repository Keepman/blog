package com.example.blog.service;

import com.example.blog.entity.Account;


/**
 * @Author: ymt
 * @Date: 2019/8/6 10:23
 * @Version 1.0
 */
public interface LoginService {
    void register(Account account);

    Account login(String username, String password);

    boolean isRepeat(String username);
}
