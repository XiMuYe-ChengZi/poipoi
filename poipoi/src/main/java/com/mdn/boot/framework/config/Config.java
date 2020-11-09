package com.mdn.boot.framework.config;

import com.mdn.boot.framework.interceptor.session.RedisSessionInterceptor;
import com.mdn.boot.framework.interceptor.thread.ThreadParameterIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 修改Spring mvc的基本配置
 * 启动时初始化system_cfg_tbl
 * @ClassName: Config.java
 * @author Mr.Chen->MengDaNai
 * @version 1.0
 * @Date 2019年1月29日 下午5:57:31
 */
@Configuration
public class Config implements WebMvcConfigurer {

	/** 请求拦截器 **/
	private ThreadParameterIntercepter threadParameterIntercepter;

	/** 登录状态拦截器 **/
	private RedisSessionInterceptor redisSessionInterceptor;

	@Autowired
	public Config(ThreadParameterIntercepter threadParameterIntercepter, RedisSessionInterceptor redisSessionInterceptor) {
		super();
		this.threadParameterIntercepter = threadParameterIntercepter;
		this.redisSessionInterceptor = redisSessionInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(threadParameterIntercepter);
		registry.addInterceptor(redisSessionInterceptor);
	}

	/**
	 * 静态资源文件配置
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

}
