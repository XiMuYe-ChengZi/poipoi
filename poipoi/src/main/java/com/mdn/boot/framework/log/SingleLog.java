package com.mdn.boot.framework.log;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({})
@Retention(RUNTIME)
public @interface SingleLog {

	/**
	 * 日志信息值
	 * @return
	 */
	String value() default "";

	/**
	 * 日志的等级
	 * @return
	 */
	LevelEnum levelEnum() default LevelEnum.INFO;
}
