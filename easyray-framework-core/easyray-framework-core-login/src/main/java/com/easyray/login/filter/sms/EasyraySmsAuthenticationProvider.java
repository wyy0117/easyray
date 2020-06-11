package com.easyray.login.filter.sms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020/6/11
 * @Author: wyy
 */
@Component
public class EasyraySmsAuthenticationProvider extends DaoAuthenticationProvider {

    public EasyraySmsAuthenticationProvider(EasyraySmsUserDetailServiceImpl userDetailService) {
        setUserDetailsService(userDetailService);
    }

    /**
     * 验证码检查
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
