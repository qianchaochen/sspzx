package com.gionee.sspzx.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.vo.sdk.SdkRequestVO;

public class TestValidator {
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	public static void main(String[] args) {
		SdkRequestVO requestVO=new SdkRequestVO();
		Set<ConstraintViolation<SdkRequestVO>> constraintViolations = validator
				.validate(requestVO);
		for (ConstraintViolation<SdkRequestVO> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
}
