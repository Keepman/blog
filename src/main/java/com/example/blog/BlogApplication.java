package com.example.blog;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import java.util.Properties;

@MapperScan("com.example.blog.dao")
@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
    /**
     * 配置PageHelper
     */
    @Bean
    public PageHelper pageHelper() {
        System.out.println("开始配置数据分页插件");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        // 当该参数设置为true时，使用RowBounds分页时，会将offset参数当成pageNum使用，可以用页码和页面大小两个参数进行分页。
        properties.setProperty("offsetAsPageNum", "true");
        // 当该参数设置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        // 当该参数设置为true时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        properties.setProperty("reasonable", "true");
        // 当该参数设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page类型）
        properties.setProperty("pageSizeZero", "false");
        //配置mysql数据库的方言，可选值为oracle,mysql,mariadb,sqlite,hsqldb,postgresql,没有默认值，必须指定该属性
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
