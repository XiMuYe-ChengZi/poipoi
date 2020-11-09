package com.mdn.boot.framework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 *************************************
 * @Description Springboot 启动成功后调用run方法
 * @author MengDaNai
 * @version v1.0
 * @Date 2019年11月19日
 *************************************
 */
@Configuration
@Slf4j
public class BootstrapReadData implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		log.info("Spring Boot started successfully");
	}
}
