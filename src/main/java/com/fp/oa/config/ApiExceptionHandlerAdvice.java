package com.fp.oa.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.oa.core.exception.ApiException;
import com.fp.oa.core.result.ResultBean;

@ControllerAdvice
public class ApiExceptionHandlerAdvice {

	/**
	 * Handle exceptions thrown by handlers.
	 * It will return a Error ResultBean with 500 status code and error msg.
	 * Note that if ApiException contains error code in future, msg will contains that
	 */
	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public ResponseEntity<ResultBean> exception(ApiException e,HttpServletResponse response) {
		ResultBean exceptionResult=new ResultBean();
		exceptionResult.setCode(ResultBean.FAIL);
		exceptionResult.setMsg(e.getMessage());
	    ResponseEntity<ResultBean> responseEntity = new ResponseEntity<>(exceptionResult,HttpStatus.INTERNAL_SERVER_ERROR);
	    return responseEntity;
	}
}
