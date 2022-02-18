package com.lihuking.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.lihuking.gmall.user.mapper")
@SpringBootApplication
public class GmallUserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserManagerApplication.class, args);
    }

}
