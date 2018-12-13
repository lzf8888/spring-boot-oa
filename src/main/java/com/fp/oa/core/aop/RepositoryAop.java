package com.fp.oa.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class RepositoryAop {

	@Value("${ht.persistence.limit:800}")
	private int limit;

	@Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
	public void executeSql() {
	}

	@Around("executeSql()")
	public Object profile(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		// logger.debug("JVM memory in use = "+ (Runtime.getRuntime().totalMemory() -
		// Runtime.getRuntime().freeMemory()));
		Object output = null;
		try {
			output = pjp.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		long elapsedTime = System.currentTimeMillis() - start;
		if (elapsedTime > limit) {
			log.warn("repo method execution time too long on " + pjp.getTarget() + "." + pjp.getSignature()
					+ ": Execution time: " + elapsedTime + " ms.");
		}
		return output;
	}

}
