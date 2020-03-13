package com.cajr.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author CAJR
 * @date 2020/3/13 2:57 下午
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.quartz")
    public DataSource quartzDataSource(){
        return new DruidDataSource();
    }
}
