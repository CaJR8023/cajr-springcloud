package com.cajr.service.impl;

import com.cajr.lock.DistributedLock;
import com.cajr.lock.impl.RedisDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CAJR
 * @date 2019/12/2 3:09 下午
 */
public class CustomTokenServices extends DefaultTokenServices {

    private static final String LOCK_KEY = "token_lock";

    private RedisTemplate redisTemplate;

    public CustomTokenServices(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken token;
        Object principal = authentication.getUserAuthentication().getPrincipal();
        UserDetails userDetails;
        CustomUserDetailsImpl user;
        CustomAdminDetailsImpl admin;
        DefaultOAuth2AccessToken oAuth2AccessToken;

        Map<String, Object> userInfoMap = new HashMap<>();
        if (principal instanceof CustomAdminDetailsImpl) {
            userDetails = (CustomAdminDetailsImpl) principal;
            admin = (CustomAdminDetailsImpl) principal;
            userInfoMap.put("user_id",admin.getId());
            userInfoMap.put("user_name",userDetails.getUsername());
        } else {
            user = (CustomUserDetailsImpl) principal;
            userInfoMap.put("user_id",user.getUserId());
            userInfoMap.put("user_name",user.getUsername());
        }

        String lockLogo = null;
        DistributedLock distributedLock = new RedisDistributedLock(redisTemplate,LOCK_KEY,60);
        try {
            do {
                lockLogo = distributedLock.acquireLock();
            } while (lockLogo == null);
            token = super.createAccessToken(authentication);
            oAuth2AccessToken = (DefaultOAuth2AccessToken) token;
            oAuth2AccessToken.setAdditionalInformation(userInfoMap);
        }finally {
            while(true){
                boolean unLock = distributedLock.releaseLock(lockLogo);
                if (unLock){
                    break;
                }
            }
        }

        return oAuth2AccessToken;
    }
}
