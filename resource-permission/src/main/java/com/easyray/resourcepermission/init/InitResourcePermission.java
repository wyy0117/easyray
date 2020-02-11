package com.easyray.resourcepermission.init;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.idgeneratorapi.service.IdService;
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
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.error.RoleErrorCode;
import com.easyray.roleapi.provider.RoleLocalProvider;
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
public class InitResourcePermission implements IEasyInit {

    @Autowired
    private ResourcePermissionConfigurationProperties resourcePermissionConfigurationProperties;
    @Autowired
    private ResourcePermissionVersionLocalProvider resourcePermissionVersionLocalService;
    @Reference
    private IdService idService;
    @Reference(check = false)
    private RoleLocalProvider roleLocalService;
    @Autowired
    private ResourceActionLocalProvider resourceActionLocalService;
    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalService;

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
        ResourcePermissionVersion resourcePermissionVersion = resourcePermissionVersionLocalService.fetchByModule(module);
        if (resourcePermissionVersion == null) {//第一次添加，没有resourcePermissionVersion
            resourcePermissionVersion = new ResourcePermissionVersion(idService.nextId(ResourcePermissionVersion.class.getName()));
            resourcePermissionVersion.setModule(module)
                    .setVersion(resourcePermissionsXML.getVersion());
            resourcePermissionVersionLocalService.save(resourcePermissionVersion);

            List<ResourcePermissionXML> resourcePermissionXMLList = resourcePermissionsXML.getResourcePermissionXMLList();

            for (ResourcePermissionXML resourcePermissionXML : resourcePermissionXMLList) {
                Role role = roleLocalService.fetchByName(resourcePermissionXML.getRoleName());
                if (role == null) {
                    throw new EntityNotExistException(new CustomThrowable(RoleErrorCode.ROLE_NOT_EXIST, resourcePermissionXML.getRoleName()));
                }
                List<ResourceAction> resourceActionList = resourceActionLocalService.fetchByNameAndActions(resourcePermissionXML.getEntityName(), resourcePermissionXML.getActionKeys());
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
                resourcePermissionLocalService.save(resourcePermission);
            }
        } else if (!resourcePermissionVersion.getVersion().equals(resourcePermissionsXML.getVersion())) {//版本有变化
            resourcePermissionVersion.setVersion(resourcePermissionsXML.getVersion());
            resourcePermissionVersionLocalService.saveOrUpdate(resourcePermissionVersion);

            List<ResourcePermissionXML> resourcePermissionXMLList = resourcePermissionsXML.getResourcePermissionXMLList();
            for (ResourcePermissionXML resourcePermissionXML : resourcePermissionXMLList) {
                Role role = roleLocalService.fetchByName(resourcePermissionXML.getRoleName());
                if (role == null) {
                    throw new EntityNotExistException(new CustomThrowable(RoleErrorCode.ROLE_NOT_EXIST, resourcePermissionXML.getRoleName()));
                }
                List<ResourceAction> resourceActionList = resourceActionLocalService.fetchByNameAndActions(resourcePermissionXML.getEntityName(), resourcePermissionXML.getActionKeys());
                if (resourceActionList.size() != resourcePermissionXML.getActionKeys().size()) {//xml中有action在数据库中找不到
                    List<String> dbActionList = resourceActionList.stream().map(ResourceAction::getAction).collect(Collectors.toList());
                    List<String> notInDbActionList = resourcePermissionXML.getActionKeys().stream().filter(name -> !dbActionList.contains(name)).collect(Collectors.toList());
                    throw new EasyCustomException(new CustomThrowable(ResourcePermissionErrorCode.ACTION_NOT_EXIST, notInDbActionList.toString()));
                }
                int actionIds = resourceActionList.stream().mapToInt(ResourceAction::getBitwiseValue).sum();

                ResourcePermission resourcePermission = resourcePermissionLocalService.fetchByNameAndRoleId(resourcePermissionXML.getEntityName(), role.getId())
                        .setScope(resourcePermissionXML.getScope())
                        .setRoleId(role.getId())
                        .setActionIds(actionIds);
                resourcePermissionLocalService.save(resourcePermission);
            }
        }
    }
}
