package com.fp.oa.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.fail;

import java.sql.Timestamp;
import java.time.Instant;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.Main;
import com.fp.oa.sample.persistence.model.SampleEntity;
import com.fp.oa.test.BaseTest;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE, classes = Main.class)
public class BeanValidatorsTest extends BaseTest{

	@Autowired    
	private Validator validator;
	
	@Test
	public void entityValidationTest() {
		SampleEntity entity=new SampleEntity();
		entity.setDesc("haha desc");
		entity.setStartTime(Timestamp.from(Instant.now()));
		try {
			BeanValidators.validateWithException(validator, entity);
			fail("Expected a ConstraintViolationException to be thrown");
		}catch(ConstraintViolationException e) {
			assertThat(e.getMessage()).contains("null");
		}
		
	}
}
