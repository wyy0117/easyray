package com.easyray.userapi.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
public enum UserErrorCode implements ICustomErrorCode {
    USERNAME_EXIST("001", "用户名已存在"),
    EMAIL_EXIST("002", "邮箱已存在");

    private String _code;
    private String _reason;

    UserErrorCode(String _code, String _reason) {
        this._code = _code;
        this._reason = _reason;
    }

    @Override
    public String code() {
        return _code;
    }

    @Override
    public String reason() {
        return _reason;
    }

}
