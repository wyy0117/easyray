package com.easyray.documentapi.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
public enum DFileErrorCode implements ICustomErrorCode {
;
    private String _code;
    private String _reason;

    DFileErrorCode(String _code, String _reason) {
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
