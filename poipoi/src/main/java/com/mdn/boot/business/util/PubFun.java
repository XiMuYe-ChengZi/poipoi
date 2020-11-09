
package com.mdn.boot.business.util;

import java.util.Objects;

import com.mdn.boot.framework.constant.SystemConstant;
import com.mdn.boot.framework.exception.BaseException;

/*
 * @Descripttion: PubFun
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-13 17:50:33
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-24 01:20:35
 */
public final class PubFun {

	private PubFun(){}

	/**
	 * 判断是否为空
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:49:09
	 * @param object
	 * @param code
	 * @return void
	 */
	public static void isNull(Object object, String msg) {
		if (Objects.isNull(object)) {
			throw new BaseException(msg);
		}
	}

	/**
	 * 判断是否为空
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:49:09
	 * @param object
	 * @param code
	 * @return void
	 */
	public static void isNull(Object object, int code) {
		if (Objects.isNull(object)) {
			throw new BaseException(code);
		}
	}

	/**
	 * 判断是否为空
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:49:18
	 * @param object
	 * @param code
	 * @param msg
	 * @return void
	 */
	public static void isNull(Object object, int code, String msg) {
		if (Objects.isNull(object)) {
			throw new BaseException(code, msg);
		}
	}
	
	/**
	 * Object转int
	 * @author MengDaNai
	 * @Date 2019年3月4日 下午5:49:30
	 * @param param
	 * @return int
	 */
	public static int objectToInt(Object param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return stringToInt(objectToString(param));
	}
	public static int objectStrongToInt(Object obj) {
		return Objects.isNull(obj)?0:stringToInt(objectToString(obj));
    }
	
	/**
	 * Object转String
	 * @author MengDaNai
	 * @Date 2019年3月4日 下午5:50:16
	 * @param param
	 * @return String
	 */
	public static String objectToString(Object param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return param.toString();
	}
	public static String objectStrongToString(Object param) {
		return Objects.isNull(param)?"":param.toString();
	}
	
	/**
	 * Object转long
	 * @author MengDaNai
	 * @Date 2019年2月1日 下午5:51:13
	 * @param param
	 * @return long
	 */
	public static long objectToLong(Object param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Long.valueOf(objectToString(param));
	}
	public static long objecStrongtToLong(Object param) {
		return Objects.isNull(param)?0:Long.valueOf(objectToString(param));
	}
	
	/**
	 * Object转double
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:51:23
	 * @param param
	 * @return double
	 */
	public static double objectToDouble(Object param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Double.parseDouble(objectToString(param));
	}
	public static double objectStrongToDouble(Object param) {
		return Objects.isNull(param)?0.00:Double.parseDouble(objectToString(param));
	}
	
	/**
	 * String转int
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:51:34
	 * @param param
	 * @return int
	 */
	public static int stringToInt(String param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Integer.parseInt(param);
	}
	public static int stringStrongToInt(String param) {
		return Objects.isNull(param)?0:Integer.parseInt(param);
	}
	
	/**
	 * String转long
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:51:40
	 * @param param
	 * @return long
	 */
	public static long stringToLong(String param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Long.valueOf(param);
	}
	public static long stringStrongToLong(String param) {
		return Objects.isNull(param)?0:Long.valueOf(param);
	}
	
	/**
	 * String转double
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:51:45
	 * @param param
	 * @return double
	 */
	public static double stringToDouble(String param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Double.parseDouble(param);
	}
	public static double stringStrongToDouble(String param) {
		return Objects.isNull(param)?0.00:Double.parseDouble(param);
	}
	
	/**
	 * int转string
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:51:55
	 * @param param
	 * @return String
	 */
	public static String intToString(int param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return String.valueOf(param);
	}
	public static String intStrongToString(int param) {
		return param==0?"":String.valueOf(param);
	}
	
	/**
	 * int转double
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:02
	 * @param param
	 * @return double
	 */
	public static double intToDouble(int param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return param;
	}
	public static double intStrongToDouble(int param) {
		return param==0?0.00:param;
	}
	
	/**
	 * int转long
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:11
	 * @param param
	 * @return long
	 */
	public static long intToLong(int param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return (long) param;
	}
	public static long intStrongToLong(int param) {
		return param==0?0:param;
	}
	
	/**
	 * double转long
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:19
	 * @param param
	 * @return long
	 */
	public static long doubleToLong(double param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Math.round(param);
	}
	public static long doubleStrongToLong(double param) {
		return param==0?0:Math.round(param);
	}
	
	/**
	 * double转String
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:26
	 * @param param
	 * @return String
	 */
	public static String doubleToString(double param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return String.valueOf(param);
	}
	public static String doubleStrongToString(double param) {
		return param==0?"":String.valueOf(param);
	}
	
	/**
	 * double转int
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:33
	 * @param param
	 * @return int
	 */
	public static int doubleToInt(double param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return (int) param;
	}
	public static int doubleStrongToInt(double param) {
		return param==0?0:(int) param;
	}
	
	/**
	 * Long转int
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:52:45
	 * @param param
	 * @return int
	 */
	public static int longToInt(Long param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return Integer.parseInt(longToString(param));
	}
	public static int longStrongToInt(Long param) {
		return param==0?0:Integer.parseInt(longToString(param));
	}
	
	/**
	 * Long转string
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:53:02
	 * @param param
	 * @return String
	 */
	public static String longToString(Long param) {
		isNull(param, SystemConstant.DATA_NO_ISEMPTY_CODE);
		return param.toString();
	}
	public static String longStrongToString(Long param) {
		return param==0?"":param.toString();
	}

	public static <T> T conversionClass(Class<T> clazz){
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new BaseException("实例化失败："+clazz.getName());
		} catch (IllegalAccessException e) {
			throw new BaseException("无法访问类："+clazz.getName());
		}
	}
}
