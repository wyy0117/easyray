package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.annotation.Unique;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@TableName("sys_resource_action_version")
@Table(name = "sys_resource_action_version")
public class ResourceActionVersion extends PrimeKeyEntity<Long> {

    @Unique(name = "uni_module_version")
    @Column(name = "module", type = MySqlDataType.VARCHAR, length = 75, nullable = false)
    private String module;

    @Unique(name = "uni_module_version")
    @Column(name = "version", type = MySqlDataType.VARCHAR, length = 20, nullable = false)
    private String version;

    public ResourceActionVersion(Long id) {
        super(id);
    }

    public ResourceActionVersion() {
    }

    @Override
    public String toString() {
        return "ResourceActionVersion{" +
                "module='" + module + '\'' +
                ", version='" + version + '\'' +
                "} " + super.toString();
    }

    public String getModule() {
        return module;
    }

    public ResourceActionVersion setModule(String module) {
        this.module = module;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public ResourceActionVersion setVersion(String version) {
        this.version = version;
        return this;
    }
}
