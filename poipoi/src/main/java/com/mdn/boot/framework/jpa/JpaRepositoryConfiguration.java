package com.mdn.boot.framework.jpa;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 功能描述:  Jpa的自动配置类   
 * @ClassName: JpaRepositoryConfiguration.java
 * @author Mr.Chen
 * @version 1.0
 * @Date 2019年7月27日 下午6:01:07
 * @see 自动配置org.springframework.boot.autoconfigure.orm.jpa.JpaRepositoriesAutoConfiguration  
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@ConditionalOnClass({JpaBaseConfiguration.class,HibernateJpaAutoConfiguration.class,BaseRepositoryImpl.class,BaseRepositoryFactoryBean.class})
public class JpaRepositoryConfiguration {

	JpaRepositoryConfiguration(){}
	
	@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
	static class EnableBaseRepository{
		
	}
	
}
