package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author
 * @Date 2019/8/20 8:45
 * 留言
 */
@Data
public class Message {
    // 留言主键ID
    private int id;
    // 留言者ID
    private int messageCommenterId;
    // 留言日期
    private String messageDate;
    // 留言内容
    private String messageContent;
    // 留言文章ID
    private Long articleId;
}
