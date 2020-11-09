package com.mdn.boot.framework.log;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface TaskLog {

	/**
	 * 名字
	 * @return
	 */
	String name() default "";

	/**
	 * 自定义日志的集合
	 * @return
	 */
	SingleLog[] singleLog() default {};

}
