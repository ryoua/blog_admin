package com.ryoua.myblog.controller.annotation;

import java.lang.annotation.*;

/**
 * @Author ryoua Created on 2019-05-03
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "user";

}
