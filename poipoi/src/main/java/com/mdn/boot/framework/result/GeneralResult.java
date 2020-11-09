/*
 * @Descripttion: 
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-13 16:50:46
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-20 02:55:27
 */
package com.mdn.boot.framework.result;

import java.io.Serializable;

import com.mdn.boot.framework.constant.SystemConstant;

/**
 * 返回的结果集
 * @ClassName: GeneralResult.java
 * @author Mr.Chen
 * @version 1.0
 * @Date 2018年10月19日 下午6:02:14
 */
public class GeneralResult implements Serializable {

	private static final long serialVersionUID = -1398120911404311855L;

	/** 返回码 **/
	private int code = SystemConstant.SUCCESS_CODE;
	
	/** 返回异常消息 **/
	private String errorMessage;
	
	/** 返回的结果 **/
	private transient Object result;
	
	/** 防止重复提交的Token **/
	private String token;
	
	public GeneralResult() {
		super();
	}

	public GeneralResult(int code, String errorMessage) {
		super();
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public GeneralResult(int code, Object result) {
		super();
		this.code = code;
		this.result = result;
	}
	
	public GeneralResult(int code, String errorMessage, Object result) {
		super();
		this.code = code;
		this.errorMessage = errorMessage;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "GeneralResult [code=" + code + ", errorMessage=" + errorMessage + ", token=" + token + "]";
	}

}
