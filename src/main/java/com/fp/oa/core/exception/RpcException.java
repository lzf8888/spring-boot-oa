/*
 * Copyright 2018 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.fp.oa.core.exception;

import java.io.Serializable;

/*
 * the service maybe rely on other remote system to provide some data or service
 * in case distributed error happens, e.g. token error, param validation violation, and network error
 * RestTemplate
 */
public class RpcException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	int code;

	public RpcException(int code, String message) {
		super(message);
		this.code = code;
	}
}
