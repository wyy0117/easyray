package com.easyray.login.filter.sms;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date: 2020/6/11
 * @Author: wyy
 */

/**
 * 用于处理短信验证码登录请求的过滤器
 */
@Component
public class EasyraySmsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";

    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;


    public EasyraySmsAuthenticationFilter(EasyraySmsAuthenticationManager authenticationManager) {
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/sms-login", RequestMethod.POST.name()));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(phoneParameter);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }
}
