package com.fp.oa.core.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class CustomFilter extends GenericFilterBean {

	/*
	 * only simulate the authentication
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if(authentication==null) {
			Collection<GrantedAuthority> authorities=new ArrayList<>();
			GrantedAuthority authority=new SimpleGrantedAuthority("user");// so when the user @PreAuthorize("hasAuthority('user')") the will get through 
			authorities.add(authority);
			CustomPrincipal principal=new CustomPrincipal();
			principal.setUserName("michael");
			principal.setEmail("micheal@test.com");
			Authentication auth=new UsernamePasswordAuthenticationToken(principal,"N/A",authorities);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}

}
