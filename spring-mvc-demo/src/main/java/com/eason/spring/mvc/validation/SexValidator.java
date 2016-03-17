package com.eason.spring.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eason.spring.mvc.domain.SexEnum;
import com.eason.spring.mvc.validation.constraint.Sex;

public class SexValidator implements ConstraintValidator<Sex, String> {

	@Override
	public void initialize(Sex sex) {
		// do nothing
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		return SexEnum.eligible(value);
	}

}
