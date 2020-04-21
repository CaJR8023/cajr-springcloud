package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CAJR
 * @date 2020/4/20 4:56 下午
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
public class ColumnApp {
    public static void main(String[] args) {
        SpringApplication.run(ColumnApp.class, args);
    }
}
