package com.osbilisim.aop.managements;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;



@Aspect
public class TimeElapsedCalculate {
	
	 @Pointcut("execution(* com.osbilisim.controllers.*.*(..)) && @annotation(com.osbilisim.aops.TimeElapsed)")
	 public void businessMethods() { }
	 
	  @Around("businessMethods()")
      public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		  StopWatch stopWatch = new StopWatch();
			stopWatch.start();

			Object retVal = pjp.proceed();

			stopWatch.stop();

			StringBuffer logMessage = new StringBuffer();
			logMessage.append(pjp.getTarget().getClass().getName());
			logMessage.append(".");
			logMessage.append(pjp.getSignature().getName());
			logMessage.append("(");
			// append args
			Object[] args = pjp.getArgs();
			for (int i = 0; i < args.length; i++) {
				logMessage.append(args[i]).append(",");
			}
			if (args.length > 0) {
				logMessage.deleteCharAt(logMessage.length() - 1);
			}

			logMessage.append(")");
			logMessage.append(" execution time: ");
			logMessage.append(stopWatch.getTotalTimeMillis());
			logMessage.append(" ms");
			System.out.println(logMessage.toString());
			return retVal;
      }


}
