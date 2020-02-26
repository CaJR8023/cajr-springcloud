package com.cajr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CAJR
 * @date 2020/1/13 4:48 下午
 */
@RestController
@Api(tags = "jwt security 接口")
@Validated
public class ConsumerSecurityController {

    @Autowired
    private RestOperations restTemplate;

    public static final String SECURITY_HOST = "http://cajr-security-auth-server";

    private String url(HttpServletRequest request){
        String uri = request.getRequestURI();
        return SECURITY_HOST + uri;
    }

    private HttpHeaders authorizationHeaders(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String authorization = request.getHeader("Authorization");
        headers.set("Authorization",authorization);

        return headers;
    }

    @ApiOperation(value = "获取token接口", httpMethod = "POST", nickname = "postAccessToken")
    @PostMapping("/oauth/token")
    Object postAccessToken(@RequestParam("grant_type") String grantType, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        String url = url(request);
        LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",grantType);
        map.add("username",username);
        map.add("password",password);
        HttpHeaders headers = authorizationHeaders(request);
        HttpEntity<LinkedMultiValueMap> entity = new HttpEntity<>(map,headers);
        return restTemplate.postForEntity(url,entity,Object.class);
    }

    @ApiOperation(value = "检查token列表接口",httpMethod = "GET" ,nickname = "checkToken")
    @GetMapping("/oauth/check_token")
    Object checkToken(@RequestParam("token") String token,HttpServletRequest request){
        String url = url(request) + "?token=" + token;
        HttpHeaders headers = authorizationHeaders(request);
        HttpEntity<LinkedMultiValueMap> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET,entity,Object.class);
    }

    @ApiOperation(value = "短信验证码登录", httpMethod = "POST", nickname = "smsCodeLogin")
    @PostMapping("/sms/login")
    Object smsCodeLogin(@RequestParam("mobile") String mobile, @RequestParam("inputCode") String inputCode, HttpServletRequest request) {
        String url = url(request);
        LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("mobile",mobile);
        map.add("inputCode",inputCode);
        HttpHeaders headers = authorizationHeaders(request);
        HttpEntity<LinkedMultiValueMap> entity = new HttpEntity<>(map,headers);
        return restTemplate.postForEntity(url,entity,Object.class);
    }
}
