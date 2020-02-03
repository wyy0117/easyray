package com.easyray.resourcepermission.entity.xml;

import java.util.List;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class ResourceActionXML {
    private String entityName;
    private List<String> actionKeys;

    @Override
    public String toString() {
        return "ResourceActionXML{" +
                "entityName='" + entityName + '\'' +
                ", actionKeys=" + actionKeys +
                '}';
    }

    public String getEntityName() {
        return entityName;
    }

    public ResourceActionXML setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public List<String> getActionKeys() {
        return actionKeys;
    }

    public ResourceActionXML setActionKeys(List<String> actionKeys) {
        this.actionKeys = actionKeys;
        return this;
    }
}
