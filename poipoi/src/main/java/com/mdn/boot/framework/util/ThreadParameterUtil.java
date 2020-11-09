package com.mdn.boot.framework.util;

import com.mdn.boot.business.entity.UserInfoTbl;
import com.mdn.boot.business.util.PubFun;
import com.mdn.boot.framework.constant.SystemConstant;
import com.mdn.boot.framework.result.GeneralResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * 线程参数的工具类
 * 
 * @ClassName: ThreadParameterUtil.java
 * @author MengDaNai
 * @version 1.0
 * @Date 2019年1月29日 下午6:04:05
 */
public class ThreadParameterUtil {

	/** 存储每个线程的返回结果 **/
	private static final ThreadLocal<GeneralResult> LOCAL_RESULT = new ThreadLocal<>();

	private static final ThreadLocal<HttpServletRequest> LOCAL_REQUEST = new ThreadLocal<>();

	private static final ThreadLocal<HttpServletResponse> LOCAL_RESPONSE = new ThreadLocal<>();
	
	private static final ThreadLocal<UserInfoTbl> LOCAL_USER = new ThreadLocal<>();
	
	private static final ThreadLocal<String> UUID = new ThreadLocal<>();

	public static void putResult(GeneralResult result) {
		LOCAL_RESULT.set(result);
	}

	public static GeneralResult getResult() {
		return LOCAL_RESULT.get();
	}

	public static void removeResult() {
		LOCAL_RESULT.remove();
	}

	public static void putRequest(HttpServletRequest httpServletRequest) {
		LOCAL_REQUEST.set(httpServletRequest);
	}

	public static HttpServletRequest getRequest() {
		return LOCAL_REQUEST.get();
	}

	public static void removeRequest() {
		LOCAL_REQUEST.remove();
	}

	public static void putResponse(HttpServletResponse httpServletResponse) {
		LOCAL_RESPONSE.set(httpServletResponse);
	}

	public static HttpServletResponse getResponse() {
		return LOCAL_RESPONSE.get();
	}

	public static void removeResponse() {
		LOCAL_RESPONSE.remove();
	}

	public static void putUser(UserInfoTbl userInfoTbl) {
		LOCAL_USER.set(userInfoTbl);
	}

	@NotNull
	public static UserInfoTbl getUser() {
		PubFun.isNull(LOCAL_USER.get(), SystemConstant.NO_DATA_OBTAINED_CODE);
		return LOCAL_USER.get();
	}

	public static void removeUser() {
		LOCAL_USER.remove();
	}
	
	public static void putUUID(String uuid) {
		UUID.set(uuid);
	}

	public static String getUUID() {
		return UUID.get();
	}

	public static void removeUUID() {
		UUID.remove();
	}

}
