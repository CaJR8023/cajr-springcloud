package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author CAJR
 * @create 2019/11/23 2:18
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
@EnableFeignClients("com.cajr.service")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableCaching
public class UserFrontApp {
    public static void main(String[] args) {
        SpringApplication.run(UserFrontApp.class,args);
    }
}
