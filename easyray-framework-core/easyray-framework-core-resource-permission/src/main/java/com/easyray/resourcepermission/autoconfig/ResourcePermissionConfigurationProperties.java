package com.easyray.resourcepermission.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 20-2-5
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(prefix = "resourcepermission.filepath")
public class ResourcePermissionConfigurationProperties {
    private String resourceAction = "permission/resource-action.xml";
    private String resourcePermission = "permission/resource-permission.xml";

    public String getResourceAction() {
        return resourceAction;
    }

    public ResourcePermissionConfigurationProperties setResourceAction(String resourceAction) {
        this.resourceAction = resourceAction;
        return this;
    }

    public String getResourcePermission() {
        return resourcePermission;
    }

    public ResourcePermissionConfigurationProperties setResourcePermission(String resourcePermission) {
        this.resourcePermission = resourcePermission;
        return this;
    }
}
