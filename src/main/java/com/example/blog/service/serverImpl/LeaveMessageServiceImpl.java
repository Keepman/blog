package com.example.blog.service.serverImpl;

import com.example.blog.dao.LeaveMessageMapper;
import com.example.blog.entity.Message;
import com.example.blog.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mayn
 * @Date 2019/8/20 9:03
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    @Override
    public void insertMessage(Message message) {
        leaveMessageMapper.insertMessage(message);
    }

    @Override
    public void DeleteMessageById(int id) {
        leaveMessageMapper.DeleteMessageById(id);
    }

    @Override
    public List<Message> selectMessageByArticleId(long articleId) {
        return leaveMessageMapper.selectMessageByArticleId(articleId);
    }
}
