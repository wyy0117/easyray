package com.easyray.auth.entity;

/**
 * @Date: 20-2-1
 * @Author: wyy
 */
public class JwtToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JwtToken setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
