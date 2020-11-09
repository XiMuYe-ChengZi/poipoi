package com.mdn.boot.business.service.impl;

import com.mdn.boot.business.repository.PcUserRepository;
import com.mdn.boot.business.service.PcUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 *************************************************
 * 	业务层实现
 * @author MengDaNai
 * @version 1.0
 * @date 2019年2月1日 创建文件
 * @See
 *************************************************
 */
@Service
public class PcUserServerImpl implements PcUserServer {
	
	private PcUserRepository pcUserRepository;
	
	@Autowired
	public PcUserServerImpl(PcUserRepository pcUserRepository) {
		this.pcUserRepository = pcUserRepository;
	}

}

