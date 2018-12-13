package com.fp.oa.core.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;

/*
 * extended validation annotation
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsJsonlValidatorImpl.class)
@Repeatable(com.fp.oa.core.validate.IsJson.List.class)
@NotEmpty
public @interface IsJson {

	String message() default "Not Valid Json";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
	@Retention(RUNTIME)
	@Documented
	public @interface List {

		IsJson[] value();
	}
}
