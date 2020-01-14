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
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Integer sendCode(String code,String phone) {
        try {
            Map<String,Object> codeTemplateMap = new HashMap<>(8);
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
}
