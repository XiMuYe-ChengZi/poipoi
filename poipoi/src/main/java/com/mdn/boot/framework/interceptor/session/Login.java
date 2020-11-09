package com.mdn.boot.framework.interceptor.session;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Login {

	/** 判断是否登入 **/
	boolean checkLogin() default true;

	/** 判断是否获取User **/
	boolean checkUser() default true;
}
