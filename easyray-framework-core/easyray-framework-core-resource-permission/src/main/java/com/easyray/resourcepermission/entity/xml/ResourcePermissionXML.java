package com.easyray.resourcepermission.entity.xml;

import java.util.List;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class ResourcePermissionXML {
    private String roleName;
    private int scope;
    private String entityName;
    private List<String> actionKeys;

    @Override
    public String toString() {
        return "ResourcePermissionXML{" +
                "roleName='" + roleName + '\'' +
                ", scope=" + scope +
                ", entityName='" + entityName + '\'' +
                ", actionKeys=" + actionKeys +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public ResourcePermissionXML setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public int getScope() {
        return scope;
    }

    public ResourcePermissionXML setScope(int scope) {
        this.scope = scope;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public ResourcePermissionXML setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public List<String> getActionKeys() {
        return actionKeys;
    }

    public ResourcePermissionXML setActionKeys(List<String> actionKeys) {
        this.actionKeys = actionKeys;
        return this;
    }
}
