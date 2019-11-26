package com.cajr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author CAJR
 * @date 2019/11/26 11:45 上午
 */
@SpringBootApplication
@MapperScan("com.cajr.mapper")
@EnableTransactionManagement
@EnableAuthorizationServer
public class JwtAuthServerApp {
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthServerApp.class,args);
    }
}
