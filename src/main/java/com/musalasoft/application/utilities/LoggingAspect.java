package com.musalasoft.application.utilities;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void applicationPackagePointCut() {
		
	}
	
	@AfterThrowing(pointcut = "applicationPackagePointCut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	}

}
