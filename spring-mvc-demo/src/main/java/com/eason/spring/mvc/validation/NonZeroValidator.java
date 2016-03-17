package com.eason.spring.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eason.spring.mvc.validation.constraint.NonZero;

public class NonZeroValidator implements ConstraintValidator<NonZero, Integer> {

	@Override
	public void initialize(NonZero constraintAnnotation) {
		// do nothing
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value != 0;
	}

}
