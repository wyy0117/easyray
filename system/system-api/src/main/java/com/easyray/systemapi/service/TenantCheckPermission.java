package com.easyray.systemapi.service;

import com.easyray.baseapi.provider.BaseCheckerPermission;
import com.easyray.systemapi.entity.Tenant;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
public interface TenantCheckPermission extends BaseCheckerPermission<Tenant>, TenantLocalProvider {
}
