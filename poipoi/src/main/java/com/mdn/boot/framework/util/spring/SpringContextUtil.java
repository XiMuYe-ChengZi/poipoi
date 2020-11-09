package com.mdn.boot.framework.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:  获取到Spring IOC容器中的Bean,用于处理Shiro与Spring Actuator冲突    
 * @ClassName: SpringContextUtil.java
 * @author MengDaNai
 * @version 1.0
 * @Date 2019年3月6日 下午6:04:20
 */
@Configuration
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

}
