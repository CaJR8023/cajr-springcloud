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
    public Integer sendCode(String code) {
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
