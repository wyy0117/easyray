package com.wyy.common.exception.filter;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
public class ErrorMsgDTO {

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 报错的原因,给用户看
     */
    private String reason;
    /**
     * 代码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    public ErrorMsgDTO() {
    }

    public ErrorMsgDTO(String errorCode, String reason, String code, String message) {
        this.errorCode = errorCode;
        this.reason = reason;
        this.code = code;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorMsgDTO setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorMsgDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ErrorMsgDTO setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorMsgDTO setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "ErrorMsgDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", reason='" + reason + '\'' +
                ", path='" + code + '\'' +
                '}';
    }
}
