package com.easyray.systemprovider.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "system.group")
public class SystemGroupProperties {

    /**
     * 系统group的名字
     */
    private String name = "system-group";

    @Override
    public String toString() {
        return "SystemGroupProperties{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
