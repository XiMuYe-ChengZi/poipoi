package com.mdn.boot.framework.constant;

/*
 * @Descripttion: 公用返回的msg
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-13 16:50:46
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-20 01:01:30
 */
public class SystemConstant extends SystemHttpConstant{
    
    /** 伪删除状态: true:0 有效**/
	public static final Integer DELETED_TRUE = 0;
	
	/** 伪删除状态: false:1 无效**/
	public static final Integer DELETED_FALSE = 1;
	
	/** 默认的页容量大小 **/
	public static final int DEFAULT_PAGESIZE_10 = 10;
	public static final int DEFAULT_PAGESIZE_20 = 20;
	
	/**	数据校验不通过 **/
	public static final int DATA_ILLEGALITY_CODE = INT_500;
	public static final String DATA_ILLEGALITY = "数据不合法";
	
	/** 操作失败 **/
	public static final int OPERATION_FAILED_CODE = INT_500;
	public static final String OPERATION_FAILED = "操作失败";

	/** 参数不能为空 **/
	public static final int DATA_NO_ISEMPTY_CODE = INT_422;
	public static final String DATA_NO_ISEMPTY = "参数不能为空";
	
	/**	1000_0002,没有更多的数据了 **/
	public static final int NO_MORE_DATA_CODE = INT_500;
	public static final String NO_MORE_DATA = "没有更多数据了";
	
	/** 未获取到数据 **/
	public static final int NO_DATA_OBTAINED_CODE =	INT_500 ;
	public static final String NO_DATA_OBTAINED = "未获取到数据";
	
	/**	未登入 **/
	public static final int NO_SESSION_CODE = INT_401;
	public static final String NO_SESSION = "未登入";
	
	/**	账号或密码错误 **/
	public static final int INCORRECT_USERNAME_OR_PASS_WORD_CODE = INT_422;
	public static final String INCORRECT_USERNAME_OR_PASS_WORD = "账号或密码错误";
    
}
