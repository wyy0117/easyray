package com.easyray.coreapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;

/**
 * @author wyy
 * @since 2020-02_12
 */
@TableName("sys_user_tenant_role")
@Table(name = "sys_user_tenant_role")
public class UserTenantRole extends PrimeKeyEntity<Long> {

    @Column(name = "user_id", type = BIGINT, length = 10, nullable = false)
    private long userId;
    @Column(name = "tenant_id", type = BIGINT, length = 10, nullable = false)
    private long tenantId;
    @Column(name = "role_id", type = BIGINT, length = 10, nullable = false)
    private long roleId;

    public UserTenantRole(Long id, long userId, long tenantId, long roleId) {
        super(id);
        this.userId = userId;
        this.tenantId = tenantId;
        this.roleId = roleId;
    }

    public UserTenantRole(long userId, long tenantId, long roleId) {
        this.userId = userId;
        this.tenantId = tenantId;
        this.roleId = roleId;
    }

    public UserTenantRole() {
    }

    @Override
    public String toString() {
        return "UserTenantRole{" +
                "userId=" + userId +
                ", tenantId=" + tenantId +
                ", roleId=" + roleId +
                "} " + super.toString();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
