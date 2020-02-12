package com.easyray.systemapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;

/**
 * @author wyy
 * @since 2020-02_12
 */
@TableName("sys_user_group_role")
@Table(name = "sys_user_group_role")
public class UserGroupRole extends PrimeKeyEntity<Long> {

    @Column(name = "user_id", type = BIGINT, length = 10, nullable = false)
    private long userId;
    @Column(name = "group_id", type = BIGINT, length = 10, nullable = false)
    private long groupId;
    @Column(name = "role_id", type = BIGINT, length = 10, nullable = false)
    private long roleId;

    public UserGroupRole(Long id, long userId, long groupId, long roleId) {
        super(id);
        this.userId = userId;
        this.groupId = groupId;
        this.roleId = roleId;
    }

    public UserGroupRole() {
    }

    @Override
    public String toString() {
        return "UserGroupRole{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", roleId=" + roleId +
                "} " + super.toString();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
