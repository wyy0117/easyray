package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@TableName("sys_resource_permission_version")
@Table(name = "sys_resource_permission_version")
public class ResourcePermissionVersion extends PrimeKeyEntity<Long> {
    @Column(name = "module", type = VARCHAR, length = 20, nullable = false)
    private String module;
    @Column(name = "version", type = VARCHAR, length = 20, nullable = false)
    private String version;

    public ResourcePermissionVersion(Long id) {
        super(id);
    }

    public ResourcePermissionVersion() {
    }

    @Override
    public String toString() {
        return "ResourcePermissionVersion{" +
                ", module='" + module + '\'' +
                ", version='" + version + '\'' +
                '}';
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
