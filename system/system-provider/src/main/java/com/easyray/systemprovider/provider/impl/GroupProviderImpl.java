package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.systemapi.service.GroupProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Service(filter = {"groupCheckPermissionFilter"})
@Component
public class GroupProviderImpl extends GroupLocalProviderImpl implements GroupProvider {

}
