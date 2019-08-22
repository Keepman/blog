package com.example.blog.service;

import com.example.blog.entity.Message;

import java.util.List;

/**
 * @Author ymt
 * @Date 2019/8/20 9:03
 */
public interface LeaveMessageService {
     void insertMessage(Message message);
     void DeleteMessageById(int id);
     List<Message> selectMessageByArticleId(long articleId);
     Integer selectCountMessageByArticleId(long articleId);
}
