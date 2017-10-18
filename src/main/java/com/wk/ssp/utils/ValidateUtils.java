package com.wk.ssp.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.vo.sdk.SdkAdslotVO;
import com.wk.ssp.vo.sdk.SdkAppVo;
import com.wk.ssp.vo.sdk.SdkDeviceVO;
import com.wk.ssp.vo.sdk.SdkGpsVO;
import com.wk.ssp.vo.sdk.SdkNetworkVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @description: 校验工具类
 */
public class ValidateUtils {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	public static void SDKValidate(SdkRequestVO requestVO){
		
		if(null == requestVO){
			throw new BusinessException("10000");
		}
		
		Set<ConstraintViolation<SdkRequestVO>> constraintViolations = validator
				.validate(requestVO);
		for (ConstraintViolation<SdkRequestVO> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
		
		validateApp(requestVO.getApp());
		validateDevice(requestVO.getDevice());
		validateAdslot(requestVO.getAdslot());
		validateNetwork(requestVO.getNetwork());
		if(null != requestVO.getGps()){
			validateGps(requestVO.getGps());
		}

	}
	
	private static void validateApp(SdkAppVo appVo){
		
		Set<ConstraintViolation<SdkAppVo>> constraintViolations = validator
				.validate(appVo);
		for (ConstraintViolation<SdkAppVo> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
	
	private static void validateDevice(SdkDeviceVO deviceVO){
		
		Set<ConstraintViolation<SdkDeviceVO>> constraintViolations = validator
				.validate(deviceVO);
		for (ConstraintViolation<SdkDeviceVO> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
	
	private static void validateAdslot(SdkAdslotVO adslotVO){
		
		Set<ConstraintViolation<SdkAdslotVO>> constraintViolations = validator
				.validate(adslotVO);
		for (ConstraintViolation<SdkAdslotVO> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
	
	private static void validateNetwork(SdkNetworkVo networkVo){
		
		Set<ConstraintViolation<SdkNetworkVo>> constraintViolations = validator
				.validate(networkVo);
		for (ConstraintViolation<SdkNetworkVo> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
	
	private static void validateGps(SdkGpsVO gpsVO){
		
		Set<ConstraintViolation<SdkGpsVO>> constraintViolations = validator
				.validate(gpsVO);
		for (ConstraintViolation<SdkGpsVO> constraintViolation : constraintViolations) {
			throw new BusinessException(constraintViolation.getMessage());
		}
	}
}
