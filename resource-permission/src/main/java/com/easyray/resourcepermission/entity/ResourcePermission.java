package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@TableName("sys_resource_permission")
@Table(name = "sys_resource_permission")
public class ResourcePermission extends PrimeKeyEntity<Long> {

    @Column(name = "name", type = MySqlDataType.VARCHAR, length = 75, nullable = false)
    private String name;

    @Column(name = "scope", type = MySqlDataType.INT, length = 1, nullable = false)
    private int scope;

    @Column(name = "prim_key", type = MySqlDataType.VARCHAR, length = 20, nullable = false, defaultValue = "")
    private String primKey;

    @Column(name = "role_id", type = MySqlDataType.BIGINT, length = 20, nullable = false)
    private long roleId;

    @Column(name = "owner_id", type = MySqlDataType.BIGINT, length = 20, nullable = false)
    private long ownerId;

    @Column(name = "action_ids", type = MySqlDataType.INT, length = 10, nullable = false)
    private int actionIds;

    public ResourcePermission() {
    }

    public ResourcePermission(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "ResourcePermission{" +
                ", name='" + name + '\'' +
                ", scope=" + scope +
                ", primKey='" + primKey + '\'' +
                ", roleId=" + roleId +
                ", ownerId=" + ownerId +
                ", actionIds=" + actionIds +
                '}';
    }

    public int getActionIds() {
        return actionIds;
    }

    public ResourcePermission setActionIds(int actionIds) {
        this.actionIds = actionIds;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourcePermission setName(String name) {
        this.name = name;
        return this;
    }

    public int getScope() {
        return scope;
    }

    public ResourcePermission setScope(int scope) {
        this.scope = scope;
        return this;
    }

    public String getPrimKey() {
        return primKey;
    }

    public ResourcePermission setPrimKey(String primKey) {
        this.primKey = primKey;
        return this;
    }

    public long getRoleId() {
        return roleId;
    }

    public ResourcePermission setRoleId(long roleId) {
        this.roleId = roleId;
        return this;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public ResourcePermission setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
