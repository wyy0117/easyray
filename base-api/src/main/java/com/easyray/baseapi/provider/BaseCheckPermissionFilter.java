package com.easyray.baseapi.provider;

import com.alibaba.dubbo.rpc.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Date: 20-1-29
 * @Author: wyy
 */

/**
 * @param <P> checkPermission
 */
public abstract class BaseCheckPermissionFilter<P extends BaseCheckerPermission> implements Filter {

    public abstract P getCheckPermission();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            P checkPermission = getCheckPermission();
            Method method = checkPermission.getClass().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            method.invoke(checkPermission, invocation.getArguments());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoker.invoke(invocation);
    }
}
