package com.easyray.auth.annotation;

import java.lang.annotation.*;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */

/**
 * Controller中标注了{@link EasyrayNoAuth}的方法都不会进行认证检查
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyrayNoAuth {
}
