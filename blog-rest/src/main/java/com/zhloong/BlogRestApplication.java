package com.zhloong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhloong
 */
@SpringBootApplication
@MapperScan("com.zhloong.blog.dal.dao")
public class BlogRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApplication.class, args);
        System.out.println("Gblog-Api 启动成功");
    }

}
