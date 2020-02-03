package com.easyray.resourcepermission.init;

import com.easyray.baseapi.init.IEasyIniter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
public class ResourcePermissionIniter implements IEasyIniter {
    @Override
    public int getOrder() {
        return 11;
    }

    @Override
    public void init(ApplicationArguments args) throws IOException {

    }
}
