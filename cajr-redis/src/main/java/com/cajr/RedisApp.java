package com.cajr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author CAJR
 * @date 2019/12/17 3:25 下午
 */
@SpringBootApplication
@EnableCaching
public class RedisApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
