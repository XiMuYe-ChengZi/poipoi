package com.mdn.boot.framework.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 提供自定义拓展Jpa Repository的实现
 * @ClassName: BaseRepositoryImpl.java
 * @author Mr.Chen
 * @version 1.0
 * @Date 2019年7月27日 下午6:00:43
 */
public class BaseRepositoryImpl<T, S extends Serializable> extends SimpleJpaRepository<T, S> implements BaseRepository<T, S> {

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
