package com.cajr.service.impl;

import com.cajr.lock.DistributedLock;
import com.cajr.lock.impl.RedisDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2019/12/2 3:09 下午
 */
public class CustomTokenServices extends DefaultTokenServices {

    private static final String LOCK_KEY = "token_lock";

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken token;
        String lockLogo = null;
        DistributedLock distributedLock = new RedisDistributedLock(redisTemplate,LOCK_KEY,60);
        try {
            do {
                lockLogo = distributedLock.acquireLock();
            } while (lockLogo == null);
            token = super.createAccessToken(authentication);
        }finally {
            while(true){
                boolean unLock = distributedLock.releaseLock(lockLogo);
                if (unLock){
                    break;
                }
            }
        }

        return token;
    }
}
