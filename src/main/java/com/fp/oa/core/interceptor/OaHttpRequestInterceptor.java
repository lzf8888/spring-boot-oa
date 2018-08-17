package com.fp.oa.core.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class OaHttpRequestInterceptor extends HandlerInterceptorAdapter{
	/**
	 * to trace, like zipkin, needs following elements:
	 * TraceId : don't need to change during the chains
	 * SpanId: generate by the request client;
	 * 		when server receives the request, get the spanid from the header, it the server needs to calling another
	 * 		system, then itself becomes the client, and it should generate the spanid and put it on the request header
	 * cs: client send time, should record when using httpclient or resttmeplate
	 * cr: client receive time
	 * sr: server receive time
	 * ss: server send time
	 * for a simple two-side call, it should record like cs-sr-ss-cr
	 * 
	 */
	
	private static final Logger LOGGER    = LoggerFactory.getLogger(OaHttpRequestInterceptor.class);
	private ThreadLocal<Long> startTime = new ThreadLocal<>();
	private ThreadLocal<String> traceId = new ThreadLocal<>();
	private ThreadLocal<String> parentSpanId = new ThreadLocal<>();//assume that it is long
	//private ThreadLocal<Long> sr=new ThreadLocal<>();//server receive time

	
	//in this method,we can generate trace-id, add span-id, save start time etc.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		startTime.set(System.currentTimeMillis());
		String _traceId = request.getHeader("traceid");
		String _spanId = request.getHeader("spanid");//assume that it is Long value
		traceId.set(_traceId);
		parentSpanId.set(_spanId);
		String queryString = request.getQueryString();
        String contentType = request.getContentType();
        
		if(StringUtils.isEmpty(_traceId)) {
			//add traceId or log a warning
			LOGGER.warn("this request doesn't contian traceid");
			traceId.set(UUID.randomUUID().toString());
			parentSpanId.set(null);// parent, no spanId
		}
		String url = request.getRequestURL().toString();
		LOGGER.info("【OA HTTP Interceptor:request coming】 url={}, traceId={}, spanId={}, query string={}, content type={}", url, _traceId,_spanId,queryString,contentType);
		return super.preHandle(request, response, handler);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// calc sr-cr
		Long spendTime=System.currentTimeMillis()-startTime.get();
		LOGGER.info("【OA HTTP Interceptor:request finished】traceId={}, spanId={}, cost time={}",traceId.get(),parentSpanId.get(), spendTime);
	}
}
