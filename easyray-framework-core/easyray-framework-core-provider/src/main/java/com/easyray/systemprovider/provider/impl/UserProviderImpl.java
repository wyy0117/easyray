package com.easyray.systemprovider.provider.impl;

import com.easyray.coreapi.service.UserProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@DubboService
@Service
public class UserProviderImpl extends UserLocalProviderImpl implements UserProvider {

}
