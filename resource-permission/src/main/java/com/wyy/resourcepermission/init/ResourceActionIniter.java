package com.wyy.resourcepermission.init;

import com.wyy.baseapi.init.IEasyIniter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
public class ResourceActionIniter implements IEasyIniter {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void init(ApplicationArguments args) {

    }
}
