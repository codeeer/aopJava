package com.osbilisim.aop.managements;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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
				for (Field field : args[i].getClass().getDeclaredFields()) {
				    field.setAccessible(true); // You might want to set modifier to public first.
				    Object value = field.get(args[i]); 
				    if (value != null && field.getName()=="userId") {
				    	logMessage.append(field.getName() + "=" + value+":");
				    	break;
				    }
				}
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

	  @AfterThrowing(pointcut = "execution(* com.osbilisim.controllers.*.*(..)) && @annotation(com.osbilisim.aops.TimeElapsed)", throwing = "ex")
		public void logError(JoinPoint joinPoint, Exception ex) throws IllegalArgumentException, IllegalAccessException {
			StringBuffer logMessage = new StringBuffer();
			logMessage.append(joinPoint.getTarget().getClass().getName());
			logMessage.append(".");
			logMessage.append(joinPoint.getSignature().getName());
			logMessage.append("(");
			// append args
			Object[] args = joinPoint.getArgs();
			for (int i = 0; i < args.length; i++) {			
				for (Field field : args[i].getClass().getDeclaredFields()) {
				    field.setAccessible(true); // You might want to set modifier to public first.
				    Object value = field.get(args[i]); 
				    if (value != null && field.getName()=="userId") {
				    	logMessage.append(field.getName() + "=" + value+":");
				    	break;
				    }
				}
				logMessage.append(args[i]).append(",");
			}
			if (args.length > 0) {
				logMessage.deleteCharAt(logMessage.length() -1);
			}
			logMessage.append(")");
			logMessage.append(" exception message: ");
			logMessage.append( ex.getMessage());
			System.out.println(logMessage.toString());
		
		}
}
