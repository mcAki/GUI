package com.sys.volunteer.exception;

public class SystemException extends RuntimeException {

	public SystemException(String frdMessage) {
		super(frdMessage);
	}

	public SystemException(Throwable throwable) {
		super(throwable);
	}

	public SystemException(Throwable throwable, String frdMessage) {
		super(frdMessage, throwable);
	}
}
