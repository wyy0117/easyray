package com.easyray.auth.service.impl;

import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.UserLocalProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @DubboReference(check = false)
    private UserLocalProvider userLocalProvider;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userLocalProvider.fetchByUsername(s);
        if (user != null) {
            return new UserDetailsImpl(user);
        }
        return null;
    }
}
