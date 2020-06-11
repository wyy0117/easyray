package com.easyray.login.filter.sms;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020/6/11
 * @Author: wyy
 */
@Component
public class EasyraySmsAuthenticationManager implements AuthenticationManager {

    private final EasyraySmsAuthenticationProvider easyraySmsAuthenticationProvider;

    public EasyraySmsAuthenticationManager(EasyraySmsAuthenticationProvider easyraySmsAuthenticationProvider) {
        this.easyraySmsAuthenticationProvider = easyraySmsAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return easyraySmsAuthenticationProvider.authenticate(authentication);
    }
}
