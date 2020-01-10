package com.cajr.config;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CAJR
 * @date 2020/1/10 3:59 下午
 */
@Configuration
public class CodeConfig {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Bean
    public DefaultAcsClient defaultAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessSecret);
        return new DefaultAcsClient(profile);
    }

    @Bean
    public CommonRequest request(){
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        return request;
    }
}
