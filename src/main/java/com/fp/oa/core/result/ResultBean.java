package com.fp.oa.core.result;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;
	public static final int FAIL = 1;
	public static final int NO_PERMISSION = 2;
	public static final int ILLEGAL_PARAM = 4;
	public static final int RPC_ERROR = 10000;
	public static final int INTERNAL_ERROR = 500;
	private String msg = "success";
	private int code = SUCCESS;
	private T data;

	public ResultBean() {
		super();
	}

	public ResultBean(int status, String msg, T object) {
		this.code = status;
		this.msg = msg;
		this.data = object;
	}

	public static <K> ResultBean<K> success(K data) {
		ResultBean<K> result = new ResultBean<K>(ResultBean.SUCCESS, "success", data);
		return result;
	}

	public static ResultBean<?> fail(String msg) {
		ResultBean<?> result = new ResultBean();
		result.setCode(ResultBean.FAIL);
		result.setMsg(msg);
		return result;
	}

	public static ResultBean<?> internalError(String msg) {
		ResultBean<?> result = new ResultBean();
		result.setCode(ResultBean.INTERNAL_ERROR);
		result.setMsg(msg);
		return result;
	}

	public ResultBean(T data) {
		super();
		this.data = data;
	}

	public ResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getSuccess() {
		return SUCCESS;
	}

	public static int getFail() {
		return FAIL;
	}

	public static int getNoPermission() {
		return NO_PERMISSION;
	}

}
