package com.easyray.common.exception.filter;

import java.io.Serializable;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
public interface ICustomErrorCode extends Serializable {
    /**
     * 错误码
     *
     * @return
     */
    public String code();

    /**
     * 用户看到的错误提示
     *
     * @return
     */
    public String reason();
}
