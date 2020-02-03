package com.easyray.auth.filter;

import com.google.gson.Gson;
import com.easyray.auth.entity.JwtToken;
import com.easyray.auth.entity.Login;
import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.auth.util.JwtTokenUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Date: 20-2-1
 * @Author: wyy
 */
public class JwtLoginFilter extends AbstractLoginFilter {

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication checkLogin(HttpServletRequest request, HttpServletResponse response) {

        // 从输入流中获取到登录的信息
        Login login = super.loginThreadLocal.get();
        return super.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>())
        );
    }

    @Override
    public void afterChecked(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Login login = super.loginThreadLocal.get();
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = JwtTokenUtil.createToken(userDetails.getUser(), login.getRememberMe());

        Cookie bear = new Cookie(JwtTokenUtil.TOKEN_HEADER, token);
        bear.setMaxAge(JwtTokenUtil.getExpiration(login.getRememberMe()));
        response.addCookie(bear);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new Gson().toJson(new JwtToken(token)));
    }
}
