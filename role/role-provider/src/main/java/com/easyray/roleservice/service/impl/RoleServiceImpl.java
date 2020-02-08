package com.easyray.roleservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.roleapi.service.RoleService;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@Service(filter = {"roleCheckPermissionFilter"})
@Component
public class RoleServiceImpl extends RoleLocalServiceImpl implements RoleService {

}
