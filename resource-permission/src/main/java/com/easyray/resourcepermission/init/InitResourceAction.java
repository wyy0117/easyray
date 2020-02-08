package com.easyray.resourcepermission.init;

import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.common.util.MergeUtil;
import com.easyray.idgenerator.service.IdService;
import com.easyray.resourcepermission.autoconfig.ResourcePermissionConfigurationProperties;
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
public class InitResourceAction implements IEasyInit {

    @Autowired
    private ResourcePermissionConfigurationProperties resourcePermissionConfigurationProperties;

    @Autowired
    private ResourceActionVersionLocalService resourceActionVersionLocalService;
    @Autowired
    private ResourcePermissionVersionLocalService resourcePermissionVersionLocalService;

    @Autowired
    private ResourceActionLocalService resourceActionLocalService;
    @Autowired
    private ResourcePermissionLocalService resourcePermissionLocalService;

    @Autowired
    private IdService idService;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_RESOURCE_ACTION;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {

        ClassPathResource classPathResource = new ClassPathResource(resourcePermissionConfigurationProperties.getResourceAction());
        InputStream inputStream = classPathResource.getInputStream();
        try {
            ResourceActionsXML resourceActionsXML = XMLUtil.readResourceAction(inputStream);
            String version = resourceActionsXML.getVersion();
            String module = resourceActionsXML.getModule();

            ResourceActionVersion resourceActionVersion = resourceActionVersionLocalService.fetchByModule(module);
            if (resourceActionVersion == null) {//第一次添加
                resourceActionVersion = new ResourceActionVersion(idService.nextId(ResourceActionVersion.class.getName()));
                resourceActionVersion.setModule(resourceActionsXML.getModule())
                        .setVersion(resourceActionsXML.getVersion());
                resourceActionVersionLocalService.save(resourceActionVersion);
                List<ResourceActionXML> resourceActionXMLList = resourceActionsXML.getResourceActionXMLList();
                for (ResourceActionXML resourceActionXML : resourceActionXMLList) {
                    List<String> actionKeys = resourceActionXML.getActionKeys();
                    addResourceAction(actionKeys, resourceActionXML.getEntityName(), 0);
                }
            } else {//已经有了，
                if (!resourceActionVersion.getVersion().equals(version)) {//版本发生了变化，需要最新的结果
                    resourceActionVersion.setVersion(version);
                    resourceActionVersionLocalService.saveOrUpdate(resourceActionVersion);

                    List<ResourceActionXML> resourceActionXMLList = resourceActionsXML.getResourceActionXMLList();
                    for (ResourceActionXML resourceActionXML : resourceActionXMLList) {
                        String entityName = resourceActionXML.getEntityName();
                        List<ResourceAction> resourceActionList = resourceActionLocalService.fetchByName(entityName);
                        List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                        MergeUtil.MergeResult<String> mergeResult = new MergeUtil<String>().merge(dbActionList, resourceActionXML.getActionKeys());
                        if (mergeResult.getNeedDelete().size() > 0) {//有需要删除的
                            deleteResourceAction(mergeResult.getNeedDelete(), entityName);
                        }
                        if (mergeResult.getNeedAdd().size() > 0) {//有需要新增的
                            addResourceAction(mergeResult.getNeedAdd(), entityName, resourceActionLocalService.countByName(entityName) + 1);
                        }
                    }

                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    /**
     * @param actionList
     * @param entityName
     * @param actionSize action的bitwiseVale从多少开始
     */
    private void addResourceAction(List<String> actionList, String entityName, int actionSize) {
        actionList = actionList.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < actionList.size(); i++) {
            String action = actionList.get(i);
            ResourceAction resourceAction = new ResourceAction(idService.nextId(entityName))
                    .setName(entityName)
                    .setAction(action)
                    .setBitwiseValue(((int) Math.pow(2.0, ((double) actionSize++))));
            resourceActionLocalService.save(resourceAction);
        }
    }

    private void deleteResourceAction(List<String> actionList, String entityName) {
        resourceActionLocalService.deleteByNameAndActions(entityName, actionList);
    }
}
