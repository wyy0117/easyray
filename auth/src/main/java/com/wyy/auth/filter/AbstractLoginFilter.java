package com.wyy.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public abstract class AbstractLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 这里有个坑，super.getAuthenticationManager() 为null，所以要从WebSecurityConfigurerAdapter的super.getAuthenticationManager()中取
     */
    protected AuthenticationManager authenticationManager;

    public AbstractLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        return checkLogin(request, response);
    }

    /**
     * 自定义验证登录信息
     *
     * @param request
     * @param response
     * @return
     */
    public abstract Authentication checkLogin(HttpServletRequest request, HttpServletResponse response);

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        afterChecked(request, response, chain, authResult);
    }

    /**
     * 用户登录信息验证成功后
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    public abstract void afterChecked(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException;
}
