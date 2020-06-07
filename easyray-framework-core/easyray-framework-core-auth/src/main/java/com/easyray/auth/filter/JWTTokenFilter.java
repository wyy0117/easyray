package com.easyray.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date: 20-2-1
 * @Author: wyy
 */
public class JWTTokenFilter extends AbstractTokenFilter {

    public JWTTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication checkToken(HttpServletRequest request, HttpServletResponse response) {
//        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
//        if (token == null) {
//            Cookie[] cookies = request.getCookies();
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(JwtTokenUtil.TOKEN_HEADER)) {//通过cookie中的jwt来认证
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        if (token == null) {
//            return null;
//        }
//        token = token.replace(JwtTokenUtil.TOKEN_PREFIX, "");
//        String username = JwtTokenUtil.getUsername(token);
//        if (username != null) {
//            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//        }
        return null;
    }
}
