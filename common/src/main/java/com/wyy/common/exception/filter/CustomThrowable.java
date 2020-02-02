package com.wyy.common.exception.filter;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
public class CustomThrowable extends Throwable {

    private ICustomErrorCode errorCode;
    /**
     * 异常的原因，不展示给用户
     */
    private String message;

    private Class<?> clazz;

    public CustomThrowable(ICustomErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


    public CustomThrowable(Class<?> clazz, String message) {
        this.clazz = clazz;
        this.message = message;
    }

    public ICustomErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CustomThrowable{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", clazz=" + clazz.getName() +
                "} " + super.toString();
    }
}
