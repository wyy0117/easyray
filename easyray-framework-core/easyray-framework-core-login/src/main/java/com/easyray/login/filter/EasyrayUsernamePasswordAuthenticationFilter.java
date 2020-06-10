package com.easyray.login.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */

/**
 * 处理登录请求,这里暂时先不override父类认证，后续可能密码是加密的需要解密
 */
public class EasyrayUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public EasyrayUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(new EasyrayAuthenticationSuccessHandler());
    }
}
