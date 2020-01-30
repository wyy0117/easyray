package com.wyy.auth.service.impl;

import com.wyy.auth.entity.UserDetailsImpl;
import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserLocalService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@Component("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Reference
    private UserLocalService userLocalService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userLocalService.fetchByUsername(s);
        if (user != null) {
            return new UserDetailsImpl(user);
        }
        return null;
    }
}
