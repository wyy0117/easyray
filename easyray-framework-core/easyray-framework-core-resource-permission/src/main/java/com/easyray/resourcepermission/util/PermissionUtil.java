package com.easyray.resourcepermission.util;

import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.coreapi.entity.User;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-4-27
 * @Author: wyy
 */
@Component
public class PermissionUtil {

    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @Autowired
    private SpringSecurityUtil springSecurityUtil;

    public boolean havePermission(long userId, long tenantId, String action, String name) {

        return resourcePermissionLocalProvider.findResourcePermission(userId, tenantId, action, name).size() > 0;
    }

    public boolean havePermission(long tenantId, String action, String name) {
        User user = springSecurityUtil.getOrSetUser();
        return havePermission(user.getId(), tenantId, action, name);
    }
}
