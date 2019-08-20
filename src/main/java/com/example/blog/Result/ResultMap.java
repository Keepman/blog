package com.example.blog.Result;

import lombok.Data;

import java.util.Map;

/**
 * @Author: zoulei
 * @Date: 2019/8/13 9:30
 * @Version 1.0
 */
@Data
public class ResultMap {
     Map<String,Object> message;
     String status;
}
