package com.example.blog.service.serverImpl;

import com.example.blog.dao.LoginMapper;
import com.example.blog.entity.Account;
import com.example.blog.service.LoginService;
import com.example.blog.utils.MD5Util;
import com.example.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ymt
 * @Date: 2019/8/6 10:22
 * @Version 1.0
 */

@Service
public class LoginServiceImpl implements LoginService {

    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public void register(Account account) {
        String psw = account.getUserPsw();
        String newpsw = MD5Util.md5Encrpt(psw);
        account.setUserPsw(newpsw);
        account.setUserDate(TimeUtil.getFormatDateForSix());
        int i = loginMapper.register(account);
        if (i > 0) {
            log.info("新增成功");
        }
    }

    @Override
    public Account login(String username, String password) {
        Account account = loginMapper.login(username, password);
        if (account != null && !"".equals(account)) {
            log.info("登录成功");
            return account;
        } else {
            return null;
        }
    }

    @Override
    public boolean isRepeat(String username) {
        Account account = loginMapper.isRepeat(username);
        if (account != null) {
            log.info("用户名已存在");
            return false;
        } else {
            log.info("用户名可用");
            return true;
        }
    }
}
