package com.easyray.systemprovider.provider.impl;

import com.easyray.coreapi.service.UserRoleProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@DubboService
@Service
public class UserRoleProviderImpl extends UserRoleLocalProviderImpl implements UserRoleProvider {

}
