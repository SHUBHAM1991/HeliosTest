package com.staples.dashboard.app.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.staples.dashboard.app.profiler.ExecutionTimeLogger;


/**
 * The Class ExceptionLogger.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 22, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
@Aspect
@Component
public class ExceptionLogger {
	private static final Logger logger = Logger.getLogger(ExceptionLogger.class);

	
	/**
	 * Method to log all thrown exceptions
	 * 
	 * @param jp
	 * @param targetObj
	 * @param e
	 */
	@AfterThrowing(pointcut="execution(public * *(..)) && within(com.staples.dashboard..*) && target(targetObj)", throwing="e")
	public void logException(JoinPoint jp, Object targetObj, Exception e) {
		logger.error("Exception thrown from " + 
				targetObj.getClass().getSimpleName() + "." + jp.getSignature().getName() + 
				" : " + e.getMessage(), e);
	}
}
