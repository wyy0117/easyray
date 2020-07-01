package com.easyray.common.exception;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
public class EntityAddFailedException extends EasyrayAbstractException {

    public EntityAddFailedException(Object entity) {
        super(new Throwable(entity.toString()));
    }
}
