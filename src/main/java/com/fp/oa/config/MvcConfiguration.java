package com.fp.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fp.oa.core.interceptor.OaHttpRequestInterceptor;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public OaHttpRequestInterceptor oaHttpRequestInterceptor() {
		return new OaHttpRequestInterceptor();
	}
	
	//using interceptor is more accurate than using controller aop,it is directly used in dispatchServlet
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		//add interceptor to add traceId  
        registry.addInterceptor(oaHttpRequestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
	
}
