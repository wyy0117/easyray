package com.easyray.resourcepermission.util;

import com.easyray.resourcepermission.entity.xml.ResourceActionXML;
import com.easyray.resourcepermission.entity.xml.ResourceActionsXML;
import com.easyray.resourcepermission.entity.xml.ResourcePermissionXML;
import com.easyray.resourcepermission.entity.xml.ResourcePermissionsXML;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class XMLUtil {

    public static ResourcePermissionsXML readResourcePermission(InputStream inputStream) throws DocumentException {
        ResourcePermissionsXML resourcePermissionsXML = new ResourcePermissionsXML();

        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        resourcePermissionsXML.setVersion(readVersion(rootElement));
        resourcePermissionsXML.setModule(readModule(rootElement.element("module")));
        resourcePermissionsXML.setResourcePermissionXMLList(readResourcePermissions(rootElement.element("resource-permissions")));

        return resourcePermissionsXML;
    }

    public static ResourceActionsXML readResourceAction(InputStream inputStream) throws DocumentException {
        ResourceActionsXML resourceActionsXML = new ResourceActionsXML();

        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        resourceActionsXML.setVersion(readVersion(rootElement));
        resourceActionsXML.setModule(readModule(rootElement.element("module")));
        resourceActionsXML.setResourceActionXMLList(readResourceActions(rootElement.element("resource-actions")));

        return resourceActionsXML;
    }

    private static String readVersion(Element rootElement) {
        return rootElement.attribute("version").getValue();
    }

    private static String readModule(Element moduleElement) {
        return moduleElement.getText();
    }

    private static List<ResourceActionXML> readResourceActions(Element resourceActionsElement) {
        List<ResourceActionXML> resourceActionXMLList = new ArrayList<>();
        Iterator<Element> resourceActions = resourceActionsElement.elementIterator("resource-action");
        resourceActions.forEachRemaining(resourceActionElement -> {
            ResourceActionXML resourceActionXML = new ResourceActionXML();
            Element entityNameElement = resourceActionElement.element("entity-name");
            resourceActionXML.setEntityName(entityNameElement.getText());
            Iterator<Element> actionKeys = resourceActionElement.elementIterator("action-key");
            List<String> actionKeyList = new ArrayList<>();
            while (actionKeys.hasNext()) {
                Element actionKeyElement = actionKeys.next();
                actionKeyList.add(actionKeyElement.getText());
            }
            resourceActionXML.setActionKeys(actionKeyList);
            resourceActionXMLList.add(resourceActionXML);
        });
        return resourceActionXMLList;
    }

    private static List<ResourcePermissionXML> readResourcePermissions(Element resourcePermissionsElement) {
        List<ResourcePermissionXML> authorizationXMLList = new ArrayList<>();
        Iterator<Element> authorizationElements = resourcePermissionsElement.elementIterator("resource-permission");
        authorizationElements.forEachRemaining(authorizationElement -> {
            ResourcePermissionXML authorizationXML = new ResourcePermissionXML();
            Element roleNameElement = authorizationElement.element("role-name");
            authorizationXML.setRoleName(roleNameElement.getText());
            Element scopeElement = authorizationElement.element("scope");
            authorizationXML.setScope(Integer.valueOf(scopeElement.getText()));
            Element entityNameElement = authorizationElement.element("entity-name");
            authorizationXML.setEntityName(entityNameElement.getText());
            List<String> actionKeyList = new ArrayList<>();

            Iterator<Element> actionKeyElements = authorizationElement.elementIterator("action-key");
            actionKeyElements.forEachRemaining(actionKeyElement -> {
                actionKeyList.add(actionKeyElement.getText());
            });
            authorizationXML.setActionKeys(actionKeyList);
            authorizationXMLList.add(authorizationXML);
        });
        return authorizationXMLList;
    }
}
