package com.easyray.resourcepermission.init;

import com.easyray.baseapi.init.IEasyIniter;
import com.easyray.resourcepermission.autoconfig.ResourcePermissionConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
public class ResourcePermissionIniter implements IEasyIniter {

    @Autowired
    private ResourcePermissionConfigurationProperties resourcePermissionConfigurationProperties;

    @Override
    public int getOrder() {
        return 11;
    }

    @Override
    public void init(ApplicationArguments args) throws IOException {

    }
}
