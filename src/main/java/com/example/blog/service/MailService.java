package com.example.blog.service;

/**
 * @Author mayn
 * @Date 2019/9/10 15:57
 */
public interface MailService {
    /**
     *
     * @param to 发送人邮箱
     * @param subject 邮件主题
     * @param content 邮件正文
     * @return 验证码数字
     */
    String sendSimpleMail(String to, String subject, String content);
}
