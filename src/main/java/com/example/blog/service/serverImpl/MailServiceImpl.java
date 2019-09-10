package com.example.blog.service.serverImpl;

import com.example.blog.service.MailService;
import com.example.blog.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author mayn
 * @Date 2019/9/10 15:58
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendSimpleMail(String to) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        String randomChar = RandomUtil.getRandomChar(6);
        message.setFrom(from); // 邮件发送者
        message.setTo(to); // 邮件接受者
        message.setSubject("验证码"); // 主题
        message.setText("验证码为:" + randomChar); // 内容
        mailSender.send(message);
        return randomChar;
    }

}
