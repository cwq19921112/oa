package com.chenwuqiang.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenwuqiang.oa.mapper")
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }

}
