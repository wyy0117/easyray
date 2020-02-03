package com.easyray.resourcepermission.entity.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class ResourcePermissionsXML extends AbstractPermissionXML {
    private List<ResourcePermissionXML> resourcePermissionXMLList = new ArrayList<>();

    @Override
    public String toString() {
        return "ResourcePermissionsXML{" +
                "resourcePermissionXMLList=" + resourcePermissionXMLList +
                "} " + super.toString();
    }

    public List<ResourcePermissionXML> getResourcePermissionXMLList() {
        return resourcePermissionXMLList;
    }

    public ResourcePermissionsXML setResourcePermissionXMLList(List<ResourcePermissionXML> resourcePermissionXMLList) {
        this.resourcePermissionXMLList = resourcePermissionXMLList;
        return this;
    }
}
