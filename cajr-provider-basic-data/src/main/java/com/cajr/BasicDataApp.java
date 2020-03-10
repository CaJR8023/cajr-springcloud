package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author CAJR
 * @create 2019/11/23 2:11
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
@EnableFeignClients("com.cajr.service")
@EnableDiscoveryClient
public class BasicDataApp {
    public static void main(String[] args) {
        SpringApplication.run(BasicDataApp.class,args);
    }
}
