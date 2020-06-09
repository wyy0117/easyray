package com.easyray.login.filter;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @Date: 2020/6/9
 * @Author: wyy
 */
public class EasyrayAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public EasyrayAuthenticationSuccessHandler() {
        setRedirectStrategy(new NoRedirectStrategy());
    }

}
