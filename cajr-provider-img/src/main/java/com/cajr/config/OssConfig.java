package com.cajr.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CAJR
 * @date 2020/4/3 11:22 上午
 */
@Configuration
public class OssConfig {
    @Value("${oss.endPoint}")
    private String endPoint;

    @Value("${oss.accessKey}")
    private String accessKey;

    @Value("${oss.secretKey}")
    private String secretKey;

    @Bean
    public OSS oss(){
        return new OSSClientBuilder().build(endPoint,accessKey,secretKey);
    }
}
