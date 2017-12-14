package com.sys.volunteer.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sys.volunteer.interceptor.TimeStatisticInterceptor;

/**
 * Aspect for Sampler.
 * 
 * @spring.bean id="samplerAspect"
 * 
 * @author Guoxi
 */
@Component @Aspect 
public class SamplerAspect {

	private final Log log = LogFactory.getLog(getClass());
	private Object doSample(ProceedingJoinPoint pjp, ThreadLocal<Long> tl)
			throws Throwable {
		long start = System.currentTimeMillis();
		String signatureName=pjp.getSignature().getName();
		String targetClassName=pjp.getTarget().getClass().getName();
		Object[] objMethodArgs=pjp.getArgs();
		String declaringTypeName=pjp.getSignature().getDeclaringTypeName();
		String argsStr="";
		if(objMethodArgs!=null && objMethodArgs.length>0){
			for(Object object:objMethodArgs)
			{
				if(object!=null)
					argsStr+=object.toString()+",";
			}
			if(argsStr.length()>1)
				argsStr=argsStr.substring(0, argsStr.length()-1);
		}
		try {
			
			return pjp.proceed();
		} finally {
			long last = System.currentTimeMillis() - start;
			log.info("[接口{"+declaringTypeName+"} 目标类{"+targetClassName+"} 调用方法{"+signatureName+"} 参数列表{"+argsStr+"}]execute " + last + "ms.");
			Long acc = tl.get();
			if (acc != null)
				tl.set(new Long(acc.longValue() + last));
		}
	}

	@Pointcut(" execution(* com.sys.volunteer..*Service.*(..)) ")
	private void anyBusinessMethod(){};
	
	@Pointcut(" execution(* com.sys.volunteer..*Dao.*(..)) ")
	private void anyDaoMethod(){};
	
	@Around("anyBusinessMethod()")
	public Object businessExecution(ProceedingJoinPoint pjp) throws Throwable {
		log.info("business execute");
		return doSample(pjp, TimeStatisticInterceptor.businessPerf);
	}
	
	@Around("anyDaoMethod()")
	public Object daoExecution(ProceedingJoinPoint pjp) throws Throwable {
		log.info("dao execute");
		return doSample(pjp, TimeStatisticInterceptor.daoPerf);
	}



}
