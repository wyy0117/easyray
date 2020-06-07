package com.easyray.login.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-5
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "easyray.auth")
public class EasyrayAuthConfigurationProperties {

    private String secret = "easyray";
    /**
     * 登录有效期,单位为秒
     */
    private int tokenExpiration = 3600;


    @Override
    public String toString() {
        return "EasyrayAuthConfigurationProperties{" +
                "secret='" + secret + '\'' +
                ", tokenExpiration=" + tokenExpiration +
                '}';
    }

    public String getSecret() {
        return secret;
    }

    public EasyrayAuthConfigurationProperties setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public int getTokenExpiration() {
        return tokenExpiration;
    }

    public EasyrayAuthConfigurationProperties setTokenExpiration(int tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
        return this;
    }
}
