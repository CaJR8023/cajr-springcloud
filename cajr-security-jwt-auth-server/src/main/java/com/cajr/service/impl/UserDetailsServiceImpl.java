package com.cajr.service.impl;

import com.cajr.service.UserService;
import com.cajr.vo.admin.Admin;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/25 7:02 下午
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final Optional<Admin> admin = this.userService.getOneAdminByAccount(s);

        if (admin.isPresent()){
            return new CustomAdminDetailsImpl(admin.get());
        }else {
            final Optional<User> user = userService.getOneUserByTel(s);
            return new CustomUserDetailsImpl(user.get());
        }
    }
}
