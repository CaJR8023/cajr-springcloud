package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CAJR
 * @date 2020/3/15 9:18 下午
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
public class TagApp {
    public static void main(String[] args) {
        SpringApplication.run(TagApp.class, args);
    }
}
