package com.cajr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.cajr.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author CAJR
 * @date 2020/1/10 3:32 下午
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    DefaultAcsClient client;

    @Autowired
    CommonRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer sendCode(String phone, String type) {
        try {
            Map<String,Object> codeTemplateMap = new HashMap<>(8);
            String code = (String) redisTemplate.opsForValue().get(phone);
            if ( code == null){
                code = getFourRandom();
                redisTemplate.opsForValue().set(type+"-"+phone,code, Duration.ofMinutes(2));
            }
            codeTemplateMap.put("code",code);
            JSONObject codeJson = new JSONObject(codeTemplateMap);
            request.putQueryParameter("PhoneNumbers",phone);
            request.putQueryParameter("TemplateParam", codeJson.toJSONString());
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            if ("OK".equals(jsonObject.get("Message"))){
                return 1;
            }
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    @Override
    public Integer checkCode(String code, String phone, String type) {
        String redisCode = (String) this.redisTemplate.opsForValue().get(type+"-"+phone);
        if (redisCode == null){
            return -1;
        }
        if (redisCode.equals(code)){
            return 1;
        }
        return 0;
    }

    private String getFourRandom(){
        Random random = new Random();
        StringBuilder fourCode = new StringBuilder(random.nextInt(10000) + "");
        int randLength = fourCode.length();
        if (randLength < 4){
            for (int i = 1; i < 4-randLength; i++) {
                fourCode.insert(randLength, "0");
            }
        }
        return fourCode.toString();
    }
}
