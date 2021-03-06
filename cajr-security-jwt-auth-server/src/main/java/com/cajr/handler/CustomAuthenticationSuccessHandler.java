package com.cajr.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author CAJR
 * @date 2020/2/25 4:36 下午
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功！");

        String header = httpServletRequest.getHeader("Authorization");
        if (header == null || !header.toLowerCase().startsWith("basic ")){
            throw new UnapprovedClientAuthenticationException("请求头中没有clientId");
        }

        String[] tokens = extractAndDecodeHeader(header, httpServletRequest);
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId配置信息不存在，clientID = " + clientId);
        }else if (!new BCryptPasswordEncoder().matches(clientSecret, clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配,clientId=" + clientId);
        }

        //grantType 为自定义的"customer"
        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(),"custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
//        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) ;

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authorizationServerTokenServices.createAccessToken(oAuth2Authentication)));
    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;

        try {
            decoded = Base64.getDecoder().decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
        String token = new String(decoded,StandardCharsets.UTF_8);
        int del = token.indexOf(":");

        if (del == -1){
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] {token.substring(0,del), token.substring(del+1)};
    }
}
