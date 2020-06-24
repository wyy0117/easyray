package com.easyray.resourcepermission.init;

import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.extension.init.IEasyrayInit;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.error.RoleErrorCode;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.resourcepermission.autoconfig.ResourcePermissionConfigurationProperties;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.entity.ResourcePermission;
import com.easyray.resourcepermission.entity.ResourcePermissionVersion;
import com.easyray.resourcepermission.entity.xml.ResourcePermissionXML;
import com.easyray.resourcepermission.entity.xml.ResourcePermissionsXML;
import com.easyray.resourcepermission.error.ResourcePermissionErrorCode;
import com.easyray.resourcepermission.service.ResourceActionLocalProvider;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.resourcepermission.service.ResourcePermissionVersionLocalProvider;
import com.easyray.resourcepermission.util.XMLUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
@Transactional
public class InitResourcePermission implements IEasyrayInit {

    private Logger logger = LoggerFactory.getLogger(InitResourcePermission.class);

    @Autowired
    private ResourcePermissionConfigurationProperties resourcePermissionConfigurationProperties;
    @Autowired
    private ResourcePermissionVersionLocalProvider resourcePermissionVersionLocalProvider;
    @DubboReference
    private IdService idService;
    @DubboReference(check = false)
    private RoleLocalProvider roleLocalProvider;
    @Autowired
    private ResourceActionLocalProvider resourceActionLocalProvider;
    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_RESOURCE_PERMISSION;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource(resourcePermissionConfigurationProperties.getResourcePermission());
        InputStream inputStream = classPathResource.getInputStream();
        ResourcePermissionsXML resourcePermissionsXML = XMLUtil.readResourcePermission(inputStream);
        String module = resourcePermissionsXML.getModule();
        logger.debug("init resource permission,module:{}", module);

        ResourcePermissionVersion resourcePermissionVersion = resourcePermissionVersionLocalProvider.fetchByModule(module);
        if (resourcePermissionVersion == null) {//第一次添加，没有resourcePermissionVersion
            logger.debug("no ResourcePermissionVersion in db,create ResourcePermissionVersion,version:{}", resourcePermissionsXML.getVersion());
            resourcePermissionVersion = new ResourcePermissionVersion(idService.nextId(ResourcePermissionVersion.class.getName()));
            resourcePermissionVersion.setModule(module)
                    .setVersion(resourcePermissionsXML.getVersion());
            resourcePermissionVersionLocalProvider.save(resourcePermissionVersion);

            List<ResourcePermissionXML> resourcePermissionXMLList = resourcePermissionsXML.getResourcePermissionXMLList();

            for (ResourcePermissionXML resourcePermissionXML : resourcePermissionXMLList) {
                Role role = roleLocalProvider.fetchByName(resourcePermissionXML.getRoleName());
                if (role == null) {
                    throw new EntityNotExistException(new CustomThrowable(RoleErrorCode.ROLE_NOT_EXIST, resourcePermissionXML.getRoleName()));
                }
                List<ResourceAction> resourceActionList = resourceActionLocalProvider.fetchByNameAndActions(resourcePermissionXML.getEntityName(), resourcePermissionXML.getActionKeys());
                if (resourceActionList.size() != resourcePermissionXML.getActionKeys().size()) {//xml中有action在数据库中找不到
                    List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                    List<String> notInDbActionList = resourcePermissionXML.getActionKeys().stream().filter(name -> !dbActionList.contains(name)).collect(Collectors.toList());
                    throw new EasyCustomException(new CustomThrowable(ResourcePermissionErrorCode.ACTION_NOT_EXIST, notInDbActionList.toString()));
                }
                int actionIds = resourceActionList.stream().mapToInt(ResourceAction::getBitwiseValue).sum();
                ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()))
                        .setName(resourcePermissionXML.getEntityName())
                        .setScope(resourcePermissionXML.getScope())
                        .setRoleId(role.getId())
                        .setActionIds(actionIds)
                        .setPrimKey("");
                logger.debug("create ResourcePermission:{}", resourcePermission);
                resourcePermissionLocalProvider.save(resourcePermission);
            }
        } else if (!resourcePermissionVersion.getVersion().equals(resourcePermissionsXML.getVersion())) {//版本有变化
            logger.debug("ResourcePermissionVersion not save,db version:{},new version:{},update it", resourcePermissionVersion.getVersion(), resourcePermissionsXML.getVersion());
            resourcePermissionVersion.setVersion(resourcePermissionsXML.getVersion());
            resourcePermissionVersionLocalProvider.saveOrUpdate(resourcePermissionVersion);

            List<ResourcePermissionXML> resourcePermissionXMLList = resourcePermissionsXML.getResourcePermissionXMLList();
            for (ResourcePermissionXML resourcePermissionXML : resourcePermissionXMLList) {
                Role role = roleLocalProvider.fetchByName(resourcePermissionXML.getRoleName());
                if (role == null) {
                    throw new EntityNotExistException(new CustomThrowable(RoleErrorCode.ROLE_NOT_EXIST, resourcePermissionXML.getRoleName()));
                }
                List<ResourceAction> resourceActionList = resourceActionLocalProvider.fetchByNameAndActions(resourcePermissionXML.getEntityName(), resourcePermissionXML.getActionKeys());
                if (resourceActionList.size() != resourcePermissionXML.getActionKeys().size()) {//xml中有action在数据库中找不到
                    List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                    List<String> notInDbActionList = resourcePermissionXML.getActionKeys().stream().filter(name -> !dbActionList.contains(name)).collect(Collectors.toList());
                    throw new EasyCustomException(new CustomThrowable(ResourcePermissionErrorCode.ACTION_NOT_EXIST, notInDbActionList.toString()));
                }
                int actionIds = resourceActionList.stream().mapToInt(ResourceAction::getBitwiseValue).sum();

                ResourcePermission resourcePermission = resourcePermissionLocalProvider.fetchByNameAndRoleId(resourcePermissionXML.getEntityName(), role.getId());
                if (resourcePermission == null) {
                    resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()))
                            .setName(resourcePermissionXML.getEntityName());
                }
                resourcePermission.setScope(resourcePermissionXML.getScope())
                        .setRoleId(role.getId())
                        .setActionIds(actionIds);
                logger.debug("create ResourcePermission:{}", resourcePermission);
                resourcePermissionLocalProvider.saveOrUpdate(resourcePermission);
            }
        } else {
            logger.debug("ResourcePermissionVersion not changed do nothing");
        }
    }
}
