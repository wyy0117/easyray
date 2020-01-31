package com.wyy.auth.annotation;

import java.lang.annotation.*;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyNoAuth {
}
