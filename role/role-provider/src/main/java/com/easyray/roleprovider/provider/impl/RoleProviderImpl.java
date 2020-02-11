package com.easyray.roleprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.roleapi.provider.RoleProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@Service(filter = {"roleCheckPermissionFilter"})
@Component
@Transactional
public class RoleProviderImpl extends RoleLocalProviderImpl implements RoleProvider {

}
