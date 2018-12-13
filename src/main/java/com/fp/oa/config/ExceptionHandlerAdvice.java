package com.fp.oa.config;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fp.oa.core.exception.ApiException;
import com.fp.oa.core.exception.RpcException;
import com.fp.oa.core.result.ResultBean;

import lombok.extern.slf4j.Slf4j;

//we assume that if the exception can be capture here, for a rest system, we always return code 200, means we've done the job, weather it is success or fail, we consume it;
//then the result code will tell if it really business level successful, then if not, you can follow the code the get the fail clue, along with message
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

	/**
	 * Handle exceptions thrown by handlers. It will return a Error ResultBean with
	 * 500 status code and error msg. Note that if ApiException contains error code
	 * in future, msg will contains that
	 */
	@ExceptionHandler(value = Exception.class)
	public ResultBean exception(Exception e) {
		log.error(e.getMessage(), e);// print error message
		if (e instanceof ApiException) {
			ApiException ex = (ApiException) e;
			ResultBean exceptionResult = new ResultBean();
			exceptionResult.setCode(ResultBean.FAIL);
			exceptionResult.setMsg(ex.getMessage());
			return exceptionResult;
		} else if (e instanceof ConstraintViolationException) {
			// controller request param validation violation
			ConstraintViolationException ex = (ConstraintViolationException) e;
			ResultBean exceptionResult = new ResultBean();
			exceptionResult.setCode(ResultBean.ILLEGAL_PARAM);
			exceptionResult.setMsg(ex.getMessage());
			return exceptionResult;
		} else if (e instanceof MethodArgumentNotValidException) {
			// controller request entity validation violation
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
			BindingResult result = exception.getBindingResult();
			final List<FieldError> fieldErrors = result.getFieldErrors();
			StringBuilder builder = new StringBuilder();

			for (FieldError error : fieldErrors) {
				builder.append(error.getDefaultMessage() + ",");
			}
			String message = builder.substring(0, builder.length() - 1);
			ResultBean exceptionResult = new ResultBean();
			exceptionResult.setCode(ResultBean.ILLEGAL_PARAM);
			exceptionResult.setMsg(message);
			return exceptionResult;
		} else if (e instanceof RpcException) {

		}
		// if cannot be conusmed, throw internal error instead
		return ResultBean.internalError(e.getMessage());

	}
}
