package com.fp.oa.core.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;



@Conditional(SecurityConfig.EnvironmentDetection.class)// use profile to switch the security config
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)// so the method will have ability to @PreAuthorize("hasAuthority('ADMIN')")
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CustomFilter(), AnonymousAuthenticationFilter.class);
		http
			.authorizeRequests().antMatchers("/css/**", "/index").permitAll()
			.and().authorizeRequests().antMatchers("/**").permitAll() //this means that authenticated users will have access to /**
			//.and().authorizeRequests().antMatchers("/**").anonymous() //this means that anonymous users(so it can be of no authentication) will have access,don't follow will permitAll etc.
			.and().csrf().disable()
			.headers().frameOptions().disable();

	}

	public static class EnvironmentDetection implements Condition{
		//so if the active profiles contains 'secure, this configuration file will not be included'
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String[] profiles=context.getEnvironment().getActiveProfiles();
			for(String profile:profiles) {
				if(profile.equals("secure")) {
					return false;
				}
			}
			return true;
		}
	}
}
