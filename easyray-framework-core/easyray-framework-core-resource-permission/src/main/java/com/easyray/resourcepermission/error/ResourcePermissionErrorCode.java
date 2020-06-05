package com.easyray.resourcepermission.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
public enum ResourcePermissionErrorCode implements ICustomErrorCode {
    ACTION_NOT_EXIST("060", "权限未定义");

    private String _code;
    private String _reason;

    ResourcePermissionErrorCode(String _code, String _reason) {
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
