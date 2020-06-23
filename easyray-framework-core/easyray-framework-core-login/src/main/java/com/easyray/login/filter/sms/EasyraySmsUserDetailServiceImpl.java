package com.easyray.login.filter.sms;

import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.UserLocalProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020/6/11
 * @Author: wyy
 */
@Component
public class EasyraySmsUserDetailServiceImpl implements UserDetailsService {

    @DubboReference(check = false)
    private UserLocalProvider userLocalProvider;

    /**
     * @param username 实际是phone
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userLocalProvider.fetchByPhone(username);
        if (user != null) {
            return new UserDetailsImpl(user);
        }
        return null;
    }
}

