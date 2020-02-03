package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@TableName("sys_resource_permission")
public class ResourcePermission {

    private String name;
    private int scope;
    private String primKey;
    private long roleId;
    private long ownerId;
    private long actionIds;

    @Override
    public String toString() {
        return "ResourcePermission{" +
                "name='" + name + '\'' +
                ", scope=" + scope +
                ", primKey='" + primKey + '\'' +
                ", roleId=" + roleId +
                ", ownerId=" + ownerId +
                ", actionIds=" + actionIds +
                '}';
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

    public long getActionIds() {
        return actionIds;
    }

    public ResourcePermission setActionIds(long actionIds) {
        this.actionIds = actionIds;
        return this;
    }
}
