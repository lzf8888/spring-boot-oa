package com.fp.oa.config;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fp.oa.core.exception.ApiException;
import com.fp.oa.core.result.ResultBean;

//we assume that if the exception can be capture here, for a rest system, we always return code 200, means we've done the job, weather it is success or fail, we consume it;
//then the result code will tell if it really business level successful, then if not, you can follow the code the get the fail clue, along with message
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * Handle exceptions thrown by handlers. It will return a Error ResultBean with
	 * 500 status code and error msg. Note that if ApiException contains error code
	 * in future, msg will contains that
	 */
	@ExceptionHandler(value = Exception.class)
	public ResultBean exception(Exception e) {
		if (e instanceof ApiException) {
			ApiException ex = (ApiException) e;
			ResultBean exceptionResult = new ResultBean();
			exceptionResult.setCode(ResultBean.FAIL);
			exceptionResult.setMsg(ex.getMessage());
			return exceptionResult;
		} else if (e instanceof ConstraintViolationException) {
			ConstraintViolationException ex = (ConstraintViolationException) e;
			ResultBean exceptionResult = new ResultBean();
			exceptionResult.setCode(ResultBean.ILLEGAL_PARAM);
			exceptionResult.setMsg(ex.getMessage());
			return exceptionResult;
		}

		return ResultBean.fail(e.getMessage());

	}
}
