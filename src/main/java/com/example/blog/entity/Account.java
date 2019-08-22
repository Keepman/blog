package com.example.blog.entity;

import lombok.Data;

/**
 * @Author: ymt
 * @Date: 2019/8/6 16:43
 * @Version 1.0
 */
@Data
public class Account {
    // 用户ID
    private Integer userId;
    // 用户昵称
    private String userName;
    // 账号
    private String userAdmin;
    // 密码
    private String userPsw;
    // 权限
    private String userRole;
    // 用户注册时间
    private String userDate;
    // 是否为VIP 1为是 0为否
    private int userVip;
}
