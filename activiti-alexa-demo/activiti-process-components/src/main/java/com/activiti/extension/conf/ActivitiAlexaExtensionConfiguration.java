package com.activiti.extension.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/*
 * A configuration class which will help get the custom packages scanned
 * 
 */

@Configuration
@ComponentScan(basePackages = {"com.client.bpm.common.bean"})
@PropertySource(value="classpath:client-bpm.properties",ignoreResourceNotFound=true)
public class ActivitiAlexaExtensionConfiguration{
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
