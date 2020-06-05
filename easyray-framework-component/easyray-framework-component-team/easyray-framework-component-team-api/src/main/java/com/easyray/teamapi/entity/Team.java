package com.easyray.teamapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Index;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;
import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_11
 */
@TableName("sys_team")
@Table(name = "sys_team")
public class Team extends BaseEntity<Long> {

    @Index
    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;
    @Column(name = "role_id", type = BIGINT, length = 20, nullable = false)
    private long roleId;

    @Column(name = "tenant_id", type = BIGINT, length = 20, nullable = false)
    private long tenantId;

    public Team(Long id) {
        super(id);
    }

    public Team() {
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", roleId=" + roleId +
                ", tenantId=" + tenantId +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public long getRoleId() {
        return roleId;
    }

    public Team setRoleId(long roleId) {
        this.roleId = roleId;
        return this;
    }

    public long getTenantId() {
        return tenantId;
    }

    public Team setTenantId(long tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
