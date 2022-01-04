package com.iot.test.common.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 아래 MapperScan annotation 과 더 밑에 setMapperLocation 설정이 중요
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.iot.test.mapper", annotationClass = Mapper.class, sqlSessionTemplateRef = "st")
public class DatabaseConfig {

	@Autowired
	private MyBatisProperties mp;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(mp.getDriverClassName());
		dataSource.setUrl(mp.getUrl());
		dataSource.setUsername(mp.getUsername());
		dataSource.setPassword(mp.getPassword());
		return dataSource;
	}

	@Bean("sb")
	public SqlSessionFactoryBean sqlSessionFactoryForMyBatis(DataSource dataSource,
			ApplicationContext applicationContext) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(mp.getConfigLocation()));
		//sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mp.getMapperLocation()));
		return sqlSessionFactoryBean;
	}

	@Bean("st")
	public SqlSession SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
