package com.easyray.resourcepermission.init;

import com.easyray.baseapi.init.IEasyIniter;
import com.easyray.common.util.MergeUtil;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.entity.ResourceActionVersion;
import com.easyray.resourcepermission.entity.xml.ResourceActionXML;
import com.easyray.resourcepermission.entity.xml.ResourceActionsXML;
import com.easyray.resourcepermission.service.ResourceActionLocalService;
import com.easyray.resourcepermission.service.ResourceActionVersionLocalService;
import com.easyray.resourcepermission.service.ResourcePermissionLocalService;
import com.easyray.resourcepermission.service.ResourcePermissionVersionLocalService;
import com.easyray.resourcepermission.util.XMLUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
public class ResourceActionIniter implements IEasyIniter {

    @Value("${resource-permission.fileName:permission/resource-permission.xml}")
    private String resourcePermissionFileName;

    @Autowired
    private ResourceActionVersionLocalService resourceActionVersionLocalService;
    @Autowired
    private ResourcePermissionVersionLocalService resourcePermissionVersionLocalService;

    @Autowired
    private ResourceActionLocalService resourceActionLocalService;
    @Autowired
    private ResourcePermissionLocalService resourcePermissionLocalService;

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public void init(ApplicationArguments args) throws IOException {

        ClassPathResource classPathResource = new ClassPathResource("permission/resource-action.xml");
        InputStream inputStream = classPathResource.getInputStream();
        try {
            ResourceActionsXML resourceActionsXML = XMLUtil.readResourceAction(inputStream);
            String version = resourceActionsXML.getVersion();
            String module = resourceActionsXML.getModule();

            ResourceActionVersion resourceActionVersion = resourceActionVersionLocalService.fetchByModule(module);
            if (resourceActionVersion == null) {//第一次添加

            } else {//已经有了，
                if (!resourceActionVersion.getVersion().equals(version)) {//版本发生了变化，需要最新的结果
                    List<ResourceActionXML> resourceActionXMLList = resourceActionsXML.getResourceActionXMLList();
                    for (ResourceActionXML resourceActionXML : resourceActionXMLList) {
                        String entityName = resourceActionXML.getEntityName();
                        List<ResourceAction> resourceActionList = resourceActionLocalService.fetchByEntityName(entityName);
                        List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                        MergeUtil.MergeResult<String> mergeResult = new MergeUtil<String>().merge(dbActionList, resourceActionXML.getActionKeys());
                        if (mergeResult.getNeedAdd().size() > 0) {//有需要新增的

                        }
                    }
                }
            }


        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}
