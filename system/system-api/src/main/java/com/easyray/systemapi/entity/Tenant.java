package com.easyray.systemapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.annotation.Unique;

import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_12
 */
@TableName("sys_tenant")
@Table(name = "sys_tenant")
public class Tenant extends BaseEntity<Long> {

    @Unique
    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;

    public Tenant(Long id) {
        super(id);
    }

    public Tenant() {
    }

    public String getName() {
        return name;
    }

    public Tenant setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
