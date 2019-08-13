package com.example.blog.dao;

import com.example.blog.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zoulei
 * @Date: 2019/8/6 10:26
 * @Version 1.0
 */

@Mapper
public interface LoginMapper {

    Integer register(Account account);

    Account login(@Param("username") String username, @Param("password") String password);

    Account isRepeat(@Param("username") String username);

}
