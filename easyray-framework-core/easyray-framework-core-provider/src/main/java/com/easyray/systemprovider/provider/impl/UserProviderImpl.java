package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.coreapi.service.UserProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@Service
@Component
public class UserProviderImpl extends UserLocalProviderImpl implements UserProvider {

}
