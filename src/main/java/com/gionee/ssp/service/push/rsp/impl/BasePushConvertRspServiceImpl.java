package com.gionee.ssp.service.push.rsp.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.service.push.rsp.convert.PushConvertAdmService;
import com.gionee.ssp.service.push.rsp.tracker.PushTrackerService;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
public class BasePushConvertRspServiceImpl {
	
	/**
	 * adm转换服务层
	 */
	@Autowired
	PushConvertAdmService pushConvertAdmService;
	
	/**
	 * push监播服务层
	 */
	@Autowired
	PushTrackerService pushTrackerService;
	
	/**
	 *demo的包名 
	 */
	protected String demo_package_name="com.gionee.ad.demo";

}
