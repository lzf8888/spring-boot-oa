package com.fp.oa.core.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

//use for service level bean validation
public class BeanValidators {

	public static void validateWithException(Validator validator,Object obj) throws ConstraintViolationException{
		Set<ConstraintViolation<Object>> violations =validator.validate(obj);
		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Object> constraintViolation : violations) {
				sb.append(constraintViolation.getMessage());
			}
			throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
		}
	}
}
