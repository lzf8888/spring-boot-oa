package com.fp.oa.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	//use it to validate bean's property with @NotNull etc. 
	@Bean
	public javax.validation.Validator getValidator(){
	    return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
