package com.easyray.common.exception;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
public class EntityDeleteFailedException extends EasyrayAbstractException {

    public EntityDeleteFailedException(Class clazz, Serializable id) {
        super(new Throwable(clazz.getName() + " : " + id));
    }

    public EntityDeleteFailedException(Class clazz, Collection<? extends Serializable> ids) {
        super(new Throwable(clazz.getName() + " : " + ids.toString()));
    }

    public EntityDeleteFailedException(Class clazz, String queryWrapper) {
        super(new Throwable(clazz.getName() + " : " + queryWrapper));
    }
}
