package com.cajr.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @date 2019/11/25 5:29 下午
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        List<String> errorList = new ArrayList<>();
        errorList.add(e.getMessage());
        Map map = ExceptionUtil.getExceptionMap(httpServletResponse.getStatus(),errorList);
        ExceptionUtil.setResponseParameter(httpServletResponse,HttpServletResponse.SC_UNAUTHORIZED,map);
    }
}
