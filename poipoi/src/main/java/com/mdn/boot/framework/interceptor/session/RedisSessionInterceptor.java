package com.mdn.boot.framework.interceptor.session;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mdn.boot.business.entity.UserInfoTbl;
import com.mdn.boot.framework.constant.SystemConstant;
import com.mdn.boot.framework.exception.BaseException;
import com.mdn.boot.framework.util.ThreadParameterUtil;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.WebUtils;

/**
 * 登录状态拦截器RedisSessionInterceptor
 * @ClassName: RedisSessionInterceptor.java
 * @author MengDaNai       
 * @version 1.0
 * @Date 2019年11月12日
 */
@Component
public class RedisSessionInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (handler instanceof ResourceHttpRequestHandler){
			return true;
		}
		// 获取到方法上的注解
		Login annotation = getAnnotation(handler, Login.class);
		if (Objects.isNull(annotation)) {
			return true;
		}
		// 判断是否登入 
		if (annotation.checkLogin()) {
			// 判断是否登入 true未登入、false登入
			if (!StringUtils.hasText(WebUtils.getSessionId(ThreadParameterUtil.getRequest()))){
				throw new BaseException(SystemConstant.NO_SESSION);
			} else {
				// 判断是否获取User
				checkUser(request, annotation);
			}
		}
		// 判断是否获取User
		checkUser(request, annotation);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

	/**
	 * 判断是否获取User
	 * @param request 
	 * @param annotation
	 * @return
	 */
	private void checkUser(HttpServletRequest request, Login annotation){
		if (annotation.checkUser()){
			putUser(request);
		}
	}
	
	/**
	 * 存入User
	 * @param request
	 */
	private void putUser(HttpServletRequest request){
		String stringUser = WebUtils.getSessionId(request);
		if (StringUtils.hasText(stringUser)){
			UserInfoTbl user = JSON.parseObject(WebUtils.getSessionAttribute(request, "userInfo").toString(), UserInfoTbl.class);
			ThreadParameterUtil.putUser(user);
		}
	}

	/**
	 * 获取到方法上的注解
	 * @param handler
	 * @param annotation
	 * @return
	 */
	private <T extends Annotation> T getAnnotation(Object handler, Class<T> annotation) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		return method.getAnnotation(annotation);
	}
}
