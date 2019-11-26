package com.cajr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2019/11/26 11:01 上午
 */
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        List<String> errors = new ArrayList<>();
        errors.add(oAuth2Exception.getMessage());
        Map<String,Object> map = ExceptionUtil.getExceptionMap(HttpStatus.FORBIDDEN.value(),errors);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
    }
}
