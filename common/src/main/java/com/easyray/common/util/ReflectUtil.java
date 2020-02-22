package com.easyray.common.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 20-2-22
 * @Author: wyy
 */
public class ReflectUtil {

    private static Map<String, Class> classNameMap = new HashMap<>();

    static {
        classNameMap.put("byte", byte.class);
        classNameMap.put("short", short.class);
        classNameMap.put("int", int.class);
        classNameMap.put("long", long.class);
        classNameMap.put("float", float.class);
        classNameMap.put("double", double.class);
        classNameMap.put("boolean", boolean.class);
        classNameMap.put("char", char.class);
    }

    public static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> aClass = Class.forName(clazz.getName());
        return aClass.getDeclaredMethod(methodName, parameterTypes);
    }

    public static Method getMethod(Class clazz, String methodName, String[] parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        Class[] parameterTypeArr = new Class[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            String typeName = parameterTypes[i];
            Class parameterType = classNameMap.get(typeName);
            if (parameterType == null) {
                parameterTypeArr[i] = Class.forName(typeName);
            } else {
                parameterTypeArr[i] = parameterType;
            }
        }
        return getMethod(clazz, methodName, parameterTypeArr);
    }
}
