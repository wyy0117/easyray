package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.coreapi.service.TenantProvider;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@DubboService
@Service
public class TenantProviderImpl extends TenantLocalProviderImpl implements TenantProvider {

}
