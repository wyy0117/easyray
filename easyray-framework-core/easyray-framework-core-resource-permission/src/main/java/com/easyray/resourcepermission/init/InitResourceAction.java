package com.easyray.resourcepermission.init;

import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyrayInit;
import com.easyray.common.util.MergeUtil;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.resourcepermission.autoconfig.ResourcePermissionConfigurationProperties;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.entity.ResourceActionVersion;
import com.easyray.resourcepermission.entity.xml.ResourceActionXML;
import com.easyray.resourcepermission.entity.xml.ResourceActionsXML;
import com.easyray.resourcepermission.service.ResourceActionLocalProvider;
import com.easyray.resourcepermission.service.ResourceActionVersionLocalProvider;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.resourcepermission.service.ResourcePermissionVersionLocalProvider;
import com.easyray.resourcepermission.util.XMLUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
@Transactional
public class InitResourceAction implements IEasyrayInit {

    private Logger logger = LoggerFactory.getLogger(InitResourceAction.class);

    @Autowired
    private ResourcePermissionConfigurationProperties resourcePermissionConfigurationProperties;

    @Autowired
    private ResourceActionVersionLocalProvider resourceActionVersionLocalProvider;
    @Autowired
    private ResourcePermissionVersionLocalProvider resourcePermissionVersionLocalProvider;

    @Autowired
    private ResourceActionLocalProvider resourceActionLocalProvider;
    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @DubboReference
    private IdService idService;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_RESOURCE_ACTION;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        logger.debug("InitResourceAction.init");
        ClassPathResource classPathResource = new ClassPathResource(resourcePermissionConfigurationProperties.getResourceAction());
        InputStream inputStream = classPathResource.getInputStream();
        try {
            ResourceActionsXML resourceActionsXML = XMLUtil.readResourceAction(inputStream);
            String version = resourceActionsXML.getVersion();
            logger.debug("resource action file version:{}", version);
            String module = resourceActionsXML.getModule();
            logger.debug("resource action file module:{}", module);

            ResourceActionVersion resourceActionVersion = resourceActionVersionLocalProvider.fetchByModule(module);
            if (resourceActionVersion == null) {//第一次添加
                logger.debug("resourceActionVersion is null,it is first time init...");
                resourceActionVersion = new ResourceActionVersion(idService.nextId(ResourceActionVersion.class.getName()));
                resourceActionVersion.setModule(resourceActionsXML.getModule())
                        .setVersion(resourceActionsXML.getVersion());
                resourceActionVersionLocalProvider.save(resourceActionVersion);
                List<ResourceActionXML> resourceActionXMLList = resourceActionsXML.getResourceActionXMLList();
                for (ResourceActionXML resourceActionXML : resourceActionXMLList) {
                    List<String> actionKeys = resourceActionXML.getActionKeys();
                    addResourceAction(actionKeys, resourceActionXML.getEntityName(), 0);
                }
            } else {//已经有了，
                logger.debug("find one resourceActionVersion in db,id:{}", resourceActionVersion.getId());
                if (!resourceActionVersion.getVersion().equals(version)) {//版本发生了变化，需要最新的结果
                    logger.debug("resource action version is not same,old version:{},new version:{}", resourceActionVersion.getVersion(), version);
                    resourceActionVersion.setVersion(version);
                    resourceActionVersionLocalProvider.saveOrUpdate(resourceActionVersion);

                    List<ResourceActionXML> resourceActionXMLList = resourceActionsXML.getResourceActionXMLList();
                    for (ResourceActionXML resourceActionXML : resourceActionXMLList) {
                        String entityName = resourceActionXML.getEntityName();
                        List<ResourceAction> resourceActionList = resourceActionLocalProvider.fetchByName(entityName);
                        List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                        MergeUtil.MergeResult<String> mergeResult = new MergeUtil<String>().merge(dbActionList, resourceActionXML.getActionKeys());
                        if (mergeResult.getNeedDelete().size() > 0) {//有需要删除的
                            logger.debug("delete some ResourceAction");
                            deleteResourceAction(mergeResult.getNeedDelete(), entityName);
                        }
                        if (mergeResult.getNeedAdd().size() > 0) {//有需要新增的
                            logger.debug("add some ResourceAction");
                            addResourceAction(mergeResult.getNeedAdd(), entityName, resourceActionLocalProvider.countByName(entityName) + 1);
                        }
                    }

                } else {
                    logger.debug("ResourceActionVersion not change,do nothing");
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
            ResourceAction resourceAction = new ResourceAction(idService.nextId(ResourceAction.class.getName()))
                    .setName(entityName)
                    .setAction(action)
                    .setBitwiseValue(((int) Math.pow(2.0, (actionSize++))));
            logger.debug("add ResourceAction:{}", resourceAction);
            resourceActionLocalProvider.save(resourceAction);
        }
    }

    private void deleteResourceAction(List<String> actionList, String entityName) {
        resourceActionLocalProvider.deleteByNameAndActions(entityName, actionList);
    }
}
