package org.lilacs.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
@Aspect
public class SaveErrorLog {
	private Logger logger = Logger.getLogger(SaveErrorLog.class);

	@AfterThrowing(pointcut="within(org.lilcas..*)",throwing="e")
	public void someError(Exception e) {
		StackTraceElement[] ste = e.getStackTrace();
		logger.error(ste[0]);
	}
}
