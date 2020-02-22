package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@Service
@Component
public class UserProviderImpl extends UserLocalProviderImpl implements UserProvider {

    @Override
    public void a(int b, User user) {

    }
}
