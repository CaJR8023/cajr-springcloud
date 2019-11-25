package com.cajr.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2019/11/25 5:33 下午
 */
public class ExceptionUtil {
    public static Map<String,Object> getExceptionMap(Integer status, List<String> errors){
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", String.valueOf(new Timestamp(new Date().getTime())));
        map.put("status", "401");
        map.put("errors", errors);
        return map;
    }
     public static void setResponseParameter(HttpServletResponse response, Integer status, Map body) throws ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         try {
             ObjectMapper objectMapper = new ObjectMapper();
             objectMapper.writeValue(response.getOutputStream(),body);
         } catch (Exception e) {
             throw new ServletException();
         }
     }
}
