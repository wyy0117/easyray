package com.easyray.resourcepermission.entity.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class ResourceActionsXML extends AbstractPermissionXML {
    private List<ResourceActionXML> resourceActionXMLList = new ArrayList<>();

    @Override
    public String toString() {
        return "ResourceActionsXML{" +
                "resourceActionXMLList=" + resourceActionXMLList +
                "} " + super.toString();
    }

    public List<ResourceActionXML> getResourceActionXMLList() {
        return resourceActionXMLList;
    }

    public ResourceActionsXML setResourceActionXMLList(List<ResourceActionXML> resourceActionXMLList) {
        this.resourceActionXMLList = resourceActionXMLList;
        return this;
    }
}
