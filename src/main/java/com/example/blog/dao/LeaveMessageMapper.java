package com.example.blog.dao;

import com.example.blog.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/20 9:03
 */
@Mapper
public interface LeaveMessageMapper {
    void insertMessage(Message message);

    void DeleteMessageById(int id);

    List<Message> selectMessageByArticleId(long articleId);
}
