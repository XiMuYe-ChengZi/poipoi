package com.mdn.boot.framework.constant;

/*
 * @Descripttion: http返回状态码
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-20 00:00:51
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-20 00:54:19
 */
public class SystemHttpConstant{
    
    /**	成功状态码 **/
    public static final int SUCCESS_CODE = 200;
    
    /** 请求体包含语法错误 **/
    public static final int INT_400 = 400;

    /** 需要验证用户身份 **/
    public static final int INT_401 = 401;
    
    /** 服务器拒绝执行 **/
    public static final int INT_403 = 403;

    /** 找不到目标资源 **/
    public static final int INT_404 = 404;

    /** POST 或者 PUT 请求的消息实体过大 **/
    public static final int INT_413 = 413;

    /** 服务器不支持请求中提交的数据的格式 **/
    public static final int INT_415 = 415;
    
	/** 请求格式正确，但是由于含有语义错误，无法响应 **/
    public static final int INT_422 = 422;
    
    /** 要求先决条件，如果想要请求能成功必须满足一些预设的条件要求先决条件，如果想要请求能成功必须满足一些预设的条件 **/
	public static final int INT_428 = 428;
    
    /** 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理 **/
    public static final int INT_500 = 500;

    /** 服务器不支持当前请求所需要的某个功能 **/
    public static final int INT_501 = 501;

    /** 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应 **/
    public static final int INT_502 = 502;

    /** 由于临时的服务器维护或者过载，服务器当前无法处理请求 **/
    public static final int INT_503 = 503;

}