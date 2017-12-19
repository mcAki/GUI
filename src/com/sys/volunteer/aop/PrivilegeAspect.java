package com.sys.volunteer.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.sys.volunteer.common.Privilege;

/**
 * Aspect for Sampler.
 * 
 * @spring.bean id="samplerAspect"
 * 
 * @author Guoxi
 */
//@Component @Aspect 
public class PrivilegeAspect {

	private final Log log = LogFactory.getLog(getClass());
	private Object doSample(ProceedingJoinPoint pjp)
			throws Throwable {

			Privilege annotationPrivilige=(Privilege)pjp.getSignature().getDeclaringType().getAnnotation(Privilege.class);
			if(annotationPrivilige!=null)
				System.out.println(annotationPrivilige.permissionName());
			return pjp.proceed();
		
	}

	@Pointcut(" execution(* com.sys.volunteer..*Action.*(..)) ")
	private void anyActionMethod(){};

	
	@Around("anyActionMethod()")
	public Object actionExecution(ProceedingJoinPoint pjp) throws Throwable {
		log.info("action execute");
		return doSample(pjp);
	}




}
