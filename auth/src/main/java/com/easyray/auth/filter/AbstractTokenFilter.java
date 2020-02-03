package com.easyray.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 20-2-1
 * @Author: wyy
 */
public abstract class AbstractTokenFilter extends BasicAuthenticationFilter {

    public AbstractTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public AbstractTokenFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken token = checkToken(request, response);
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(checkToken(request, response));
        super.doFilterInternal(request, response, chain);
    }

    public abstract UsernamePasswordAuthenticationToken checkToken(HttpServletRequest request, HttpServletResponse response);
}
