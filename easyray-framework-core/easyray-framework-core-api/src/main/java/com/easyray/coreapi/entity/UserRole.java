package com.easyray.coreapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Index;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
@TableName("sys_user_role")
@Table(name = "sys_user_role")
public class UserRole extends PrimeKeyEntity<Long> {

    @Index
    @Column(name = "user_id", type = MySqlDataType.BIGINT, length = 20, nullable = false)
    private long userId;

    @Index
    @Column(name = "role_id", type = MySqlDataType.BIGINT, length = 20, nullable = false)
    private long roleId;

    public UserRole(Long id, long userId, long roleId) {
        super(id);
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "} " + super.toString();
    }

    public long getUserId() {
        return userId;
    }

    public UserRole setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(long roleId) {
        this.roleId = roleId;
        return this;
    }
}
