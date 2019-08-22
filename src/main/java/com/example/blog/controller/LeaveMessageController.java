package com.example.blog.controller;

import com.example.blog.entity.Account;
import com.example.blog.entity.Message;
import com.example.blog.service.LeaveMessageService;
import com.example.blog.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.blog.utils.TimeUtil.getFormatDateForSix;

/**
 * @Author ymt
 * @Date 2019/8/20 9:00
 * 留言
 */
@RestController
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 发布留言
     * 需要传入的参数：messageContent和articleId
     */
    @RequestMapping("/insertMessage")
    public void insertMessage(Message message) {
        Account account = AccountUtils.getAccount();
        message.setMessageDate(getFormatDateForSix());
        message.setMessageCommenterId(account.getUserId());
        leaveMessageService.insertMessage(message);
    }

    /**
     * 查询留言
     */
    @RequestMapping("/selectMessageByArticleId")
    public List<Message> selectMessageByArticleId(long articleId) {
        return leaveMessageService.selectMessageByArticleId(articleId);
    }

    /**
     * 删除留言
     */
    @RequestMapping("/DeleteMessageById")
    public void deleteMessageById(int id) {
        leaveMessageService.DeleteMessageById(id);
    }
}
