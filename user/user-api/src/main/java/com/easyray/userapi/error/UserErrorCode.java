package com.easyray.userapi.error;

import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
public enum UserErrorCode implements ICustomErrorCode {
    USERNAME_EXIST() {
        @Override
        public String code() {
            return "001";
        }

        @Override
        public String reason() {
            return "用户名已存在";
        }
    },
    EMAIL_EXIST() {
        @Override
        public String code() {
            return "002";
        }

        @Override
        public String reason() {
            return "邮箱已存在";
        }
    }


}
