package com.easyray.resourcepermission.util;

import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.coreapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-4-27
 * @Author: wyy
 */
@Component
public class PermissionUtil {

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    public boolean havePermission(long userId, long tenantId, String action, String name) {

        return resourcePermissionLocalProvider.findResourcePermission(userId, tenantId, action, name).size() > 0;
    }

    public boolean havePermission(long tenantId, String action, String name) {
        User user = springSecurityThreadLocal.getUser();
        return havePermission(user.getId(), tenantId, action, name);
    }
}
