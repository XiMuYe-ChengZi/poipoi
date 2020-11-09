package com.mdn.boot.framework.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdn.boot.framework.constant.SystemConstant;

/*
 * @Descripttion: 异常类
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-13 17:55:07
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-28 18:43:42
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -1765552019491644700L;
	
	private List<Map<String, Integer>> list = new ArrayList<>();
	private Map<String, Integer> map = new HashMap<>();
	
	/** 异常码 **/
	private int code = SystemConstant.OPERATION_FAILED_CODE;
	
	/** 异常消息 **/
	private String errorMessage;
	
	public BaseException() {
		super();
		map.put(SystemConstant.OPERATION_FAILED, SystemConstant.OPERATION_FAILED_CODE);
		map.put(SystemConstant.NO_MORE_DATA, SystemConstant.NO_MORE_DATA_CODE);
		map.put(SystemConstant.NO_SESSION, SystemConstant.NO_SESSION_CODE);
		map.put(SystemConstant.INCORRECT_USERNAME_OR_PASS_WORD, SystemConstant.INCORRECT_USERNAME_OR_PASS_WORD_CODE);
		map.put(SystemConstant.DATA_NO_ISEMPTY, SystemConstant.DATA_NO_ISEMPTY_CODE);
		map.put(SystemConstant.NO_DATA_OBTAINED, SystemConstant.NO_DATA_OBTAINED_CODE);
		list.add(map);
	}
	
	public BaseException(int code) {
		this();
		this.code = code;
	}
	
	public BaseException(String errorMessage) {
		this();
		this.errorMessage = errorMessage;
	}
	
	public BaseException(int code, String errorMessage) {
		this();
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public BaseException(Throwable cause, int code, String errorMessage) {
		super(cause);
		this.code = code;
		this.errorMessage = errorMessage;
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

	public List<Map<String, Integer>> getList() {
		return list;
	}

	public void setList(List<Map<String, Integer>> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

}
