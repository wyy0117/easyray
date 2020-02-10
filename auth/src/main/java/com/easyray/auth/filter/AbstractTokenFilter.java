package com.easyray.auth.filter;

import com.easyray.auth.error.AuthError;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.common.util.ApplicationContextUtil;
import com.easyray.userapi.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

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
//
//    public AbstractTokenFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = checkToken(request, response);
        if (authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        Object principal = authentication.getPrincipal();
        SpringSecurityThreadLocal springSecurityThreadLocal = ApplicationContextUtil.getBean(SpringSecurityThreadLocal.class);
        if (principal instanceof String) {
            try {
                springSecurityThreadLocal.setUser(((String) principal));
            } catch (EntityNotExistException e) {
                e.printStackTrace();
                throw new ServletException(new CustomThrowable(AuthError.USER_ERROR, principal.toString()));
            }
        } else if (principal instanceof User) {
            springSecurityThreadLocal.setUser(((User) principal));
        } else {
            throw new ServletException(new CustomThrowable(AuthError.PRINCIPAL_ERROR, principal.toString()));
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    public abstract Authentication checkToken(HttpServletRequest request, HttpServletResponse response);
}
