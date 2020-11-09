package com.mdn.boot.framework.interceptor.thread;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mdn.boot.framework.result.GeneralResult;
import com.mdn.boot.framework.util.ThreadParameterUtil;

/**
 * 拦截每一个请求 并追加返回结果集   
 * @ClassName: ThreadParameterIntercepter.java
 * @author Mr.Chen->MengDaNai       
 * @version 1.0
 * @Date 2019年7月19日 下午5:59:39
 */
@Component
public class ThreadParameterIntercepter extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ThreadParameterUtil.putResult(new GeneralResult());
		ThreadParameterUtil.putRequest(request);
		ThreadParameterUtil.putResponse(response);
		ThreadParameterUtil.putUser(null);
		ThreadParameterUtil.putUUID(UUID.randomUUID().toString().replace("-", ""));
		//	添加跨域CORS
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed","1");
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadParameterUtil.removeResult();
		ThreadParameterUtil.removeRequest();
		ThreadParameterUtil.removeResponse();
		ThreadParameterUtil.removeUser();
		ThreadParameterUtil.removeUUID();
	}
	
}
