package com.easyray.auth.filter.dubbocontext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.*;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.common.util.ApplicationContextUtil;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserLocalProvider;

/**
 * @Date: 20-5-2
 * @Author: wyy
 */
@Activate(group = Constants.PROVIDER)
public class AuthContextProviderFilter implements Filter {

    @Reference
    private UserLocalProvider userLocalProvider;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        SpringSecurityThreadLocal springSecurityThreadLocal = ApplicationContextUtil.getBean(SpringSecurityThreadLocal.class);
        String userIdStr = RpcContext.getContext().getAttachment(FieldNameConstant.userId);

        if (springSecurityThreadLocal != null
                && userIdStr != null
                && userLocalProvider != null) {
            User user = userLocalProvider.getById(Long.parseLong(userIdStr));
            springSecurityThreadLocal.setUser(user);

        }
        return invoker.invoke(invocation);
    }
}
