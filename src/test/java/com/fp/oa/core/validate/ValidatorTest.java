/*
 * Copyright 2018 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.fp.oa.core.validate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.Main;
import com.fp.oa.test.BaseTest;

import lombok.AllArgsConstructor;
import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Main.class)
public class ValidatorTest extends BaseTest {

	@Autowired
	Validator validator;

	@Test
	public void isJsonTest() {
		JsonTestEntity entity = new JsonTestEntity("aa");
		Set<ConstraintViolation<JsonTestEntity>> validResult = validator.validate(entity);
		assertThat(validResult).isNotEmpty();
		entity = new JsonTestEntity("{ \"expert\": true }");
		validResult = validator.validate(entity);
		assertThat(validResult).isEmpty();
		entity = new JsonTestEntity(null);
		validResult = validator.validate(entity);
		assertThat(validResult).size().isEqualTo(2);
		ConstraintViolation<JsonTestEntity> validation = validResult.iterator().next();
		// assertThat(validation.getMessage()).isEqualTo("空");//多语言环境message不同
	}

	@Data
	@AllArgsConstructor
	public static class JsonTestEntity {

		@IsJson
		private String jsonValue;

	}
}
