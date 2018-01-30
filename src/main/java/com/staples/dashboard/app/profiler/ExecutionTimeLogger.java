package com.staples.dashboard.app.profiler;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeLogger {

	private static final Logger logger = Logger.getLogger(ExecutionTimeLogger.class);
	
	@Around("(execution(* com.staples.dashboard.app.controllers.*.*(..)) && @target(org.springframework.stereotype.Controller) && target(targetObj)) "
			+ "|| (execution(* com.staples.dashboard.app.service.*.*(..)) && @target(org.springframework.stereotype.Service) && target(targetObj))"
			+ "|| (execution(* com.staples.dashboard.app.dao.*.*(..)) && @target(org.springframework.stereotype.Repository) && target(targetObj))"
			+ "|| (execution(* com.staples.dashboard.app.hibernate.*.*(..)) && @target(org.springframework.stereotype.Repository) && target(targetObj))")
    public Object logServiceAccess(ProceedingJoinPoint jp, Object targetObj) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.debug(targetObj.getClass().getSimpleName() +":" + jp.getSignature().getName() + " | " +"Start time " + startTime + " ms");
        Object result = jp.proceed();
        long endTime = System.currentTimeMillis();
        logger.debug(targetObj.getClass().getSimpleName() +":" + jp.getSignature().getName() + " | " +"End time " + endTime + " ms");
        long totalTime =  endTime - startTime;
        logger.debug(targetObj.getClass().getSimpleName() +":" + jp.getSignature().getName() + " | " +"Total time " + totalTime + " ms");
        
        return result;
    }
}
