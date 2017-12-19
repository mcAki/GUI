package com.sys.volunteer.xunyuan.protocol;

/**
 * 用于反射的类 
 */
public class ResponeClass {
	private String serviceName;
	private String methodName;
	
	public ResponeClass(String serviceName, String methodName) {
		super();
		this.serviceName = serviceName;
		this.methodName = methodName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
