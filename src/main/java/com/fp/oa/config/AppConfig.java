package com.fp.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.fp.oa.core.interceptor.LoggingHttpRequestInterceptor;

@Configuration
public class AppConfig {

	//use it to validate bean's property with @NotNull etc. 
	@Bean
	public javax.validation.Validator getValidator(){
	    return new LocalValidatorFactoryBean();
	}
	
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.getInterceptors().add(new LoggingHttpRequestInterceptor());//add interceptor to record request and response
        return restTemplate;
    }
}
