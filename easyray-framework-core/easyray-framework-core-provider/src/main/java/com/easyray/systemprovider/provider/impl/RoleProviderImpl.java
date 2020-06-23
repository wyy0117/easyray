package com.easyray.systemprovider.provider.impl;

import com.easyray.coreapi.service.RoleProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@DubboService
@Service
@Transactional
public class RoleProviderImpl extends RoleLocalProviderImpl implements RoleProvider {

}
