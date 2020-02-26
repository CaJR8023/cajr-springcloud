package com.cajr.handler;

import com.cajr.exception.ExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author CAJR
 * @date 2020/2/25 4:39 下午
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("登录失败！");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        List<String> errorList = new ArrayList<>();
        errorList.add(e.getMessage());
        Map map = ExceptionUtil.getExceptionMap(httpServletResponse.getStatus(),errorList);
        ExceptionUtil.setResponseParameter(httpServletResponse,HttpServletResponse.SC_UNAUTHORIZED,map);
    }
}
