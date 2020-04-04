package com.cajr.service.impl;

import com.cajr.vo.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author CAJR
 * @date 2019/11/25 7:44 下午
 */
@Service
public class CustomUserDetailsImpl implements UserDetails {

    private User user;
    public CustomUserDetailsImpl() { }

    CustomUserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getTel();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public int getUserId(){
        return user.getId();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() >= 1;
    }

//    CustomUserDetailsImpl(User user, RoleService roleService, PermissionGroupService permissionGroupService) {
//        this.user = user;
//        this.roleService = roleService;
//        this.permissionGroupService = permissionGroupService;
//    }
}
