package com.easyray.roleapi.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
public enum RoleErrorCode implements ICustomErrorCode {

    ROLE_NOT_EXIST("030","角色不存在");

    private String _code;
    private String _reason;

    RoleErrorCode(String _code, String _reason) {
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
