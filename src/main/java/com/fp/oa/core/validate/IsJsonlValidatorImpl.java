/*
 * Copyright 2018 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.fp.oa.core.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IsJsonlValidatorImpl implements ConstraintValidator<IsJson, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
