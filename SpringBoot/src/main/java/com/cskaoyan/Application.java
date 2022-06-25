package com.cskaoyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cskaoyan.mapper") // 配置Mapper的扫描包
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
