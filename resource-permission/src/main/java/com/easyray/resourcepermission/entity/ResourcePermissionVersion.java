package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@TableName("sys_resource_permission_version")
public class ResourcePermissionVersion extends BaseEntity<Long> {
    private String module;
    private String version;

    @Override
    public String toString() {
        return "ResourcePermissionVersion{" +
                "module='" + module + '\'' +
                ", version='" + version + '\'' +
                "} " + super.toString();
    }

    public String getModule() {
        return module;
    }

    public ResourcePermissionVersion setModule(String module) {
        this.module = module;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public ResourcePermissionVersion setVersion(String version) {
        this.version = version;
        return this;
    }
}
