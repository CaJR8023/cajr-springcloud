package com.cajr.provider;

import com.cajr.authentication.CustomSmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author CAJR
 * @date 2020/2/25 3:33 下午
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private RedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomSmsCodeAuthenticationToken smsCodeAuthenticationToken = (CustomSmsCodeAuthenticationToken) authentication;

        String mobile = (String) smsCodeAuthenticationToken.getPrincipal();
        checkCode(mobile);
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        //此时鉴权成功后，应当 new 一个拥有鉴权的 authenticationResult 返回
        CustomSmsCodeAuthenticationToken authenticationResult = new CustomSmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(userDetails);
        return authenticationResult;
    }

    private void checkCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String inputCode = request.getParameter("inputCode");

        String redisCode = (String) this.redisTemplate.opsForValue().get(mobile);
        if (redisCode == null){
            throw new BadCredentialsException("验证码已过期，请重新发送！");
        }
        if (!redisCode.equals(inputCode)){
            throw new BadCredentialsException("验证码错误！");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomSmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
