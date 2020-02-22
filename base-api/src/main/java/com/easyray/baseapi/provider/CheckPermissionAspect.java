package com.easyray.baseapi.provider;

import com.easyray.common.util.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Date: 20-2-22
 * @Author: wyy
 */
public abstract class CheckPermissionAspect<C extends BaseCheckerPermission> {


    public abstract C getCheckPermission();

    //    @Pointcut("target(com.easyray.baseapi.provider.BaseProvider)")
    public abstract void pointcut();

    @Before("pointcut()")
    public void checkPermission(JoinPoint joinPoint) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = joinPoint.getSignature().toLongString();
        String substring = s.substring(s.indexOf("(") + 1, s.indexOf(")"));

        C checkPermission = getCheckPermission();
        Method method = ReflectUtil.getMethod(checkPermission.getClass(), methodName, substring.split(","));
        method.invoke(checkPermission, args);
    }
}
