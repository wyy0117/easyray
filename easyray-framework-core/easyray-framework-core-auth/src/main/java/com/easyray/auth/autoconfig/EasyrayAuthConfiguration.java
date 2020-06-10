package com.easyray.auth.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Date: 2020/6/10
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "easyray.auth")
public class EasyrayAuthConfiguration {

    /**
     * 认证白名单
     */
    private String[] whiteList = new String[0];

    @Override
    public String toString() {
        return "EasyrayAuthConfiguration{" +
                "whiteList=" + Arrays.toString(whiteList) +
                '}';
    }

    public String[] getWhiteList() {
        return whiteList;
    }

    public EasyrayAuthConfiguration setWhiteList(String[] whiteList) {
        this.whiteList = whiteList;
        return this;
    }
}
