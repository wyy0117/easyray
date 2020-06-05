package com.easyray.resourcepermission;

import com.easyray.resourcepermission.entity.xml.ResourceActionsXML;
import com.easyray.resourcepermission.entity.xml.ResourcePermissionsXML;
import com.easyray.resourcepermission.util.XMLUtil;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class XMLTest {
    @Test
    public void readResourcePermission() throws IOException, DocumentException {
        ClassPathResource classPathResource = new ClassPathResource("permission/resource-permission.xml");
        InputStream inputStream = classPathResource.getInputStream();

        ResourcePermissionsXML resourcePermissionsXML = XMLUtil.readResourcePermission(inputStream);
        System.out.println("resourcePermissionsXML = " + resourcePermissionsXML);
    }

    @Test
    public void readResourceAction() throws IOException, DocumentException {
        ClassPathResource classPathResource = new ClassPathResource("permission/resource-action.xml");
        InputStream inputStream = classPathResource.getInputStream();

        ResourceActionsXML resourceActionsXML = XMLUtil.readResourceAction(inputStream);
        System.out.println("resourceActionsXML = " + resourceActionsXML);
    }
}
