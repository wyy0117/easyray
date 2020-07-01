package com.easyray.common.exception;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
public class EntityUpdateFailedException extends EasyrayAbstractException {
    public EntityUpdateFailedException(Object entity) {
        super(new Throwable(entity.toString()));
    }
}
