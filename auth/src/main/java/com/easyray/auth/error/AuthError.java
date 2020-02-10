package com.easyray.auth.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 20-2-10
 * @Author: wyy
 */
public enum AuthError implements ICustomErrorCode {

    PRINCIPAL_ERROR("000", "获取认证信息失败"),
    USER_ERROR("001", "获取用户信息失败");

    private String _code;
    private String _reason;

    AuthError(String _code, String _reason) {
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
