package com.mdn.boot.framework.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 功能描述:  自定义拓展Jpa所提供的Repository
 * @ClassName: BaseRepository.java
 * @author Mr.Chen
 * @version 1.0
 * @Date 2018年10月29日 下午5:59:52
 */
@NoRepositoryBean
public interface BaseRepository<T, S extends Serializable> extends JpaRepository<T, S>, JpaSpecificationExecutor<T> {

}
