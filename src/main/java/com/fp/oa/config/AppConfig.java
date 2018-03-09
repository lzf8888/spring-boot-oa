package com.fp.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class AppConfig {

	//use it to validate bean's property with @NotNull etc. 
	@Bean
	public javax.validation.Validator getValidator(){
	    return new LocalValidatorFactoryBean();
	}
}
