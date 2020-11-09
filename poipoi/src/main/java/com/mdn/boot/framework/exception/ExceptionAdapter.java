package com.mdn.boot.framework.exception;

import com.mdn.boot.framework.constant.SystemConstant;
import com.mdn.boot.framework.result.GeneralResult;
import com.mdn.boot.framework.util.ThreadParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * web请求的异常处理类
 * 
 * @ClassName: ExceptionAdapter.java
 * @author MengDaNai
 * @version 1.0
 * @Date 2019年9月2日 下午4:05:18
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdapter {

	/**
	 * 全局BaseException异常捕捉点 ExceptionHandler配置精确异常后会优先进入精确异常 不会进入Exception
	 * 如果都没有就进入Exception
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:07:59
	 * @param baseException
	 * @param request
	 * @return GeneralResult
	 */
	@ExceptionHandler(BaseException.class)
	public GeneralResult handlerBaseException(BaseException baseException, HttpServletRequest request) {
		System.out.println("BaseException");
		baseException.printStackTrace();
		// 根据msg找到code
		baseException.setCode(baseException.getMap().get(baseException.getErrorMessage()));
		// 获取到异常的信息
		// baseException.printStackTrace();
		Arrays.asList(baseException.getStackTrace()).stream().filter(e -> e.getClassName().indexOf("business") > -1)
				.map(Object::toString).forEach(log::error);
		if (isAjaxRequest(request)) {
			// ajax请求 异常处理
			return this.handleExceptionOfAjaxRequest(baseException.getCode(), baseException.getErrorMessage());
		} else {
			// 普通请求 异常处理
			return this.handleExceptionOfNormalRequest(baseException.getCode(), baseException.getErrorMessage());
		}
	}

	/**
	 * 全局BindException异常捕捉点 ExceptionHandler配置精确异常后会优先进入精确异常 不会进入Exception
	 * 如果都没有就进入Exception
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:37:30
	 * @param bindException
	 * @param request
	 * @return GeneralResult
	 */
	@ExceptionHandler(BindException.class)
	public GeneralResult handlerBindException(BindException bindException, HttpServletRequest request) {
		System.out.println("bindException");
		// 获取到异常的信息
		// bindException.printStackTrace();
		if (isAjaxRequest(request)) {
			// ajax请求 异常处理
			return this.handleExceptionOfAjaxRequest(SystemConstant.INT_422,
					bindException.getFieldError().getDefaultMessage());
		} else {
			// 普通请求 异常处理
			return this.handleExceptionOfNormalRequest(SystemConstant.INT_422,
					bindException.getFieldError().getDefaultMessage());
		}
	}

	/**
	 * 全局BindException异常捕捉点</br>
	 * ExceptionHandler配置精确异常后会优先进入精确异常 不会进入Exception</br>
	 * 如果都没有就进入Exception</br>
	 * 
	 * @Author: MengDaNai
	 * @Date: 2020-04-20 01:32:37
	 * @param invalidPropertyException
	 * @param request
	 * @return:
	 */
	@ExceptionHandler(InvalidPropertyException.class)
	public GeneralResult handlerInvalidPropertyException(InvalidPropertyException invalidPropertyException,
			HttpServletRequest request) {
		System.out.println("InvalidPropertyException");
		// 获取到异常的信息
		// bindException.printStackTrace();
		if (isAjaxRequest(request)) {
			// ajax请求 异常处理
			return this.handleExceptionOfAjaxRequest(SystemConstant.INT_400,
					invalidPropertyException.getMessage());
		} else {
			// 普通请求 异常处理
			return this.handleExceptionOfNormalRequest(SystemConstant.INT_400,
					invalidPropertyException.getMessage());
		}
	}

	/**
	 * 全局Exception异常捕捉点 ExceptionHandler配置精确异常后会优先进入精确异常 不会进入Exception
	 * 如果都没有就进入Exception
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:37:54
	 * @param exception
	 * @param request
	 * @return GeneralResult
	 */
	@ExceptionHandler(Exception.class)
	public GeneralResult handlerException(Exception exception, HttpServletRequest request) {
		System.out.println("Exception");
		// log.error(exception.getMessage());
		// 记录错误日志
		// Arrays.asList(exception.getStackTrace()).stream().map(Object::toString).forEach(log::error);
		// 获取到异常的信息
		exception.printStackTrace();
		// taskLogDao.saveErrorLog(exception.getMessage(), exception.getStackTrace(),
		// LevelEnum.ERROR, TaskLogErrorEnum.EXCEPTION);
		if (isAjaxRequest(request)) {
			// ajax请求 异常处理
			return this.handleExceptionOfAjaxRequest(SystemConstant.INT_500, exception.getMessage());
		} else {
			// 普通请求 异常处理
			return this.handleExceptionOfNormalRequest(SystemConstant.INT_500, exception.getMessage());
		}
	}

	/**
	 * 判断请求是否是Ajax请求 true表示ajax请求 false表示其他类型请求
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:08:37
	 * @param request
	 * @return boolean true表示ajax请求 false表示其他类型请求
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestWith = request.getHeader("X-Requested-With");
		log.info("获取到的请求头是" + requestWith);
		// 比较忽略大小写
		return "XMLHttpRequest".equalsIgnoreCase(requestWith);
	}

	/**
	 * 处理普通请求异常 直接将errorMessage放入request作用域
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:05:53
	 * @param code
	 * @param errorMessage
	 * @return GeneralResult
	 */
	private GeneralResult handleExceptionOfNormalRequest(int code, String errorMessage) {
		// 普通请求
		// 拿到将要执行Action的result属性
		GeneralResult result = ThreadParameterUtil.getResult();
		result.setCode(code);
		result.setErrorMessage(errorMessage);
		log.info("设置的普通请求返回结果为：错误码：" + result.getCode() + ",错误信息为：" + result.getErrorMessage());
		return result;
	}

	/**
	 * 处理Ajax请求异常 将错误消息封装GenericResult对象
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月2日 下午4:06:05
	 * @param code
	 * @param errorMessage
	 * @return GeneralResult
	 */
	private GeneralResult handleExceptionOfAjaxRequest(int code, String errorMessage) {
		log.info("设置的Ajax返回结果为：错误码：" + code + ",错误信息为：" + errorMessage);
		// 拿到将要执行Action的result属性
		GeneralResult result = ThreadParameterUtil.getResult();
		result.setCode(code);
		result.setErrorMessage(errorMessage);
		return result;
	}

}
