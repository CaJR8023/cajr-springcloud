package com.cajr.service.impl;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author CAJR
 * @date 2019/11/26 10:36 上午
 */
public class CustomUserAuthenticationConverter extends JwtAccessTokenConverter {

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String,Object> result = new HashMap<>();

        Object principal = authentication.getPrincipal();

        if (token.getScope() != null){
            result.put("scope",token.getScope());
        }

        if (token.getExpiration() != null){
            result.put("exp",token.getExpiration().getTime() / 1000);
        }

        if (authentication.getOAuth2Request() != null){
            result.put("grant_type",authentication.getOAuth2Request().getGrantType());
        }

        if (principal != null){
            if (principal instanceof CustomUserDetailsImpl){
                CustomUserDetailsImpl customUserDetails = (CustomUserDetailsImpl) principal;
                result.put("user",customUserDetails);
            }
            if (principal instanceof CustomAdminDetailsImpl){
                CustomAdminDetailsImpl admin = (CustomAdminDetailsImpl) principal;
                result.put("user",admin);
            }
            result.put("user", Objects.requireNonNull(authentication.getOAuth2Request()).getGrantType());
        }
        return result;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        Object principal = authentication.getPrincipal();
        CustomUserDetailsImpl user = null;
        CustomAdminDetailsImpl admin = null;
        Map<String, Object> userInfoMap = new HashMap<>();

        if ( principal != null) {
            if ( principal instanceof CustomUserDetailsImpl) {
                user = (CustomUserDetailsImpl) principal;
                userInfoMap.put("user_id", user.getUsername());
                System.out.println(user.getUsername());
                userInfoMap.put("user_name", user.getUsername());
            } else if (principal instanceof CustomAdminDetailsImpl) {
                admin = (CustomAdminDetailsImpl) principal;
                userInfoMap.put("user_id", admin.getId());
                userInfoMap.put("user_name", admin.getUsername());
            }
        }

        defaultOAuth2AccessToken.setAdditionalInformation(userInfoMap);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}
