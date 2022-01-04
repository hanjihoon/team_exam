package com.iot.test.common.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mybatis")
public class MyBatisProperties {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private String configLocation;
	private String mapperLocation;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public String getMapperLocation() {
		return mapperLocation;
	}

	public void setMapperLocation(String mapperLocation) {
		this.mapperLocation = mapperLocation;
	}

	@Override
	public String toString() {
		return "MyBatisProperties [driverClassName=" + driverClassName + ", url=" + url + ", username=" + username
				+ ", password=" + password + ", configLocation=" + configLocation + ", mapperLocation=" + mapperLocation
				+ "]";
	}

}
