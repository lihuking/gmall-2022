package com.lihuking.gmall.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDubbo
@MapperScan("com.lihuking.gmall.user.mapper")
@SpringBootApplication
public class GmallUserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserManagerApplication.class, args);
    }

}
