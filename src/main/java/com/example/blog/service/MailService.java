package com.example.blog.service;

/**
 * @Author mayn
 * @Date 2019/9/10 15:57
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
