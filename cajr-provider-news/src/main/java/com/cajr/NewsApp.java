package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CAJR
 * @date 2020/1/15 4:12 下午
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
@EnableFeignClients("com.cajr.service")
@EnableDiscoveryClient
public class NewsApp {
    public static void main(String[] args) {
        SpringApplication.run(NewsApp.class,args);
    }
}
