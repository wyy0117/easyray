package com.easyray.login.filter;

import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.common.util.ApplicationContextUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */

/**
 * 处理登录请求
 */
public class EasyrayUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public EasyrayUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        SecurityContext currentContext = SecurityContextHolder.createEmptyContext();
        currentContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(currentContext);
        SpringSecurityThreadLocal securityThreadLocal = ApplicationContextUtil.getBean(SpringSecurityThreadLocal.class);
        securityThreadLocal.setUser(((UserDetailsImpl) authResult.getPrincipal()).getUser());
    }

}
