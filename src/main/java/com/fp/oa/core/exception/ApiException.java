package com.fp.oa.core.exception;

/*
 * this exception if for api call, in other words, controller, 
 * when controller calls services to get data, then exceptions happens,
 * and controller will capture and handle exception, or let this work been done
 * by controllerAdvice, to let the caller get the well formed error messages
 * instead of 500 exception etc.
 */
public class ApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long errorCode;
	protected Object data;

	public ApiException(Long errorCode, String message, Object data, Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
		this.data = data;
	}

	public ApiException(Long errorCode, String message, Object data) {
		this(errorCode, message, data, null);
	}

	public ApiException(Long errorCode, String message) {
		this(errorCode, message, null, null);
	}

	public ApiException(String message, Throwable e) {
		this(null, message, null, e);
	}

	public ApiException() {

	}

	public ApiException(Throwable e) {
		super(e);
	}

	public Long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}