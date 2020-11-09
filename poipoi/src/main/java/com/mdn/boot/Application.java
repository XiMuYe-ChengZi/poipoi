package com.mdn.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @Descripttion: 应用程序主入口
 * @version: v1.0
 * @Author: MengDaNai
 * @Date: 2020-04-13 16:50:44
 * @LastEditors: MengDaNai
 * @LastEditTime: 2020-04-20 00:24:17
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
