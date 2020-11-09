package com.mdn.boot.business.controller;

import com.mdn.boot.business.service.PcUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *************************************************
 *	控制层 用户操作
 * @author  MengDaNai                   
 * @version 1.0                
 * @date    2019年2月1日 创建文件                                            
 * @See                            
 *************************************************
 */
@Controller
@RequestMapping(value="/PcUser")
public class PcUser {
	
	private PcUserServer pcUserServer;
	
	@Autowired
	public PcUser(PcUserServer pcUserServer) {
		super();
		this.pcUserServer = pcUserServer;
	}

}
