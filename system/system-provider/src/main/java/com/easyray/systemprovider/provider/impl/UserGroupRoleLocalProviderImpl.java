package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.systemapi.entity.UserGroupRole;
import com.easyray.systemapi.service.UserGroupRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserGroupRoleMapper;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Service
@Component
public class UserGroupRoleLocalProviderImpl extends EasyrayServiceImpl<UserGroupRoleMapper, UserGroupRole> implements UserGroupRoleLocalProvider {


}
