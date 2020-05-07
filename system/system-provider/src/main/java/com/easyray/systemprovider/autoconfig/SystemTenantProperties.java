package com.easyray.systemprovider.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "system.tenant")
public class SystemTenantProperties {

    /**
     * 系统tenant的名字
     */
    private String name = "system-tenant";

    @Override
    public String toString() {
        return "SystemTenantProperties{" +
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
