package com.mdn.boot.business.repository;

import com.mdn.boot.business.entity.UserInfoTbl;
import com.mdn.boot.framework.jpa.BaseRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 *************************************************
 * 	功能描述: 用户的持久化接口
 * @author Mr.Chen
 * @version 1.0
 * @date 2018年11月15日 创建文件
 * @see
 *************************************************
 */
@Repository
public interface PcUserRepository extends BaseRepository<UserInfoTbl, Integer> {

}
