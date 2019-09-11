package com.example.blog.service;

/**
 * @Author ymt
 * @Date 2019/9/10 15:57
 */
public interface MailService {
    /**
     *
     * @param to 发送人邮箱
     * @return 验证码数字
     */
    String sendSimpleMail(String to);
}
