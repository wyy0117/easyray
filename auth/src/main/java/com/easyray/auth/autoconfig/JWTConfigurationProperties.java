package com.easyray.auth.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-5
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
public class JWTConfigurationProperties {

    private boolean rememberMe = false;
    private String secret = "easyray";
    /**
     * jwt有效期
     */
    private int expiration = 3600;
    /**
     * jwt记住我有效期
     */
    private int rememberMeExpiration = 86400;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public JWTConfigurationProperties setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public JWTConfigurationProperties setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public int getExpiration() {
        return expiration;
    }

    public JWTConfigurationProperties setExpiration(int expiration) {
        this.expiration = expiration;
        return this;
    }

    public int getRememberMeExpiration() {
        return rememberMeExpiration;
    }

    public JWTConfigurationProperties setRememberMeExpiration(int rememberMeExpiration) {
        this.rememberMeExpiration = rememberMeExpiration;
        return this;
    }
}
