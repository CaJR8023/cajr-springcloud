package com.cajr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author CAJR
 * @date 2019/11/26 11:27 上午
 */
@RestController
public class JwtController {
    @Autowired
    DefaultTokenServices tokenServices;

    @Autowired
    JwtAccessTokenConverter converter;

    @GetMapping("/jwt/user")
    public Object getUser(@RequestParam("token") String token){
        OAuth2AccessToken accessToken = tokenServices.readAccessToken(token);
        OAuth2Authentication auth2Authentication = tokenServices.loadAuthentication(accessToken.getValue());

        return converter.convertAccessToken(accessToken,auth2Authentication);
    }
}
