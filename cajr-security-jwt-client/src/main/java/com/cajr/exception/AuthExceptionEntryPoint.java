package com.cajr.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2019/11/25 6:07 下午
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        List<String> errorList = new ArrayList<>();
        errorList.add(e.getMessage());
        Map map = ExceptionUtil.getExceptionMap(401,errorList);
        ExceptionUtil.setResponseParameter(httpServletResponse,HttpServletResponse.SC_UNAUTHORIZED,map);
    }
}
