package com.easyray.userservice.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-5
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "user.admin")
public class UserConfigurationProperties {

    /**
     * 用户名
     */
    private String username = "admin";
    /**
     * 密码
     */
    private String password = "admin";

    public String getUsername() {
        return username;
    }

    public UserConfigurationProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserConfigurationProperties setPassword(String password) {
        this.password = password;
        return this;
    }
}
