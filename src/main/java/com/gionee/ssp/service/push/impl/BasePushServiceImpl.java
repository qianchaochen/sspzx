package com.gionee.ssp.service.push.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.service.ipush.es.AdSearchService;
import com.gionee.ssp.service.ipush.es.PushResultService;
import com.gionee.ssp.service.push.req.PushDmpService;
import com.gionee.ssp.service.push.req.convert.PushConvertReqService;
import com.gionee.ssp.service.push.rsp.PushConvertRspService;
import com.gionee.ssp.service.req.ConvertReqService;
import com.gionee.ssp.service.rsp.ConvertRspService;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public class BasePushServiceImpl {

	/**
	 * 广告搜索服务层
	 */
	@Autowired
	AdSearchService adSearchService;
	/**
	 * 结果服务层
	 */
	@Autowired
	PushResultService pushResultService;
	/**
	 *设置DMP查询条件服务层
	 */
	@Autowired
	PushDmpService pushDmpService;
	
    /**
     * req转换广告服务层
     */
    @Autowired
    ConvertReqService convertReqService;
    /**
     * rsp转换广告服务层
     */
    @Autowired
    ConvertRspService convertRspService;
    
    /**
     * push req转换广告服务层
     */
    @Autowired
    PushConvertReqService pushConvertReqService;
    /**
     * push rsp转换广告服务层
     */
    @Autowired
    PushConvertRspService pushConvertRspService;

}
