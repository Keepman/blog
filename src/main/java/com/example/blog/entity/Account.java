package com.example.blog.entity;

import lombok.Data;

/**
 * @Author: zoulei
 * @Date: 2019/8/6 16:43
 * @Version 1.0
 */
@Data
public class Account {
    // 用户ID
    private Integer roleId;
    // 用户昵称
    private String roleName;
    // 账号
    private String roleAdmin;
    // 密码
    private String rolePsw;
}
