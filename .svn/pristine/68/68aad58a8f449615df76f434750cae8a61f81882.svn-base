package com.gionee.ssp.service.adFlow.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.service.adFlow.AdFlowCtrlService;
import com.gionee.ssp.service.adFlow.AdFlowModeFilterService;
import com.gionee.ssp.service.adx.AdxListService;
import com.gionee.ssp.service.adx.AdxSelectService;
import com.gionee.ssp.service.bid.BidAdService;
import com.gionee.ssp.service.broadcast.BroadCastService;
import com.gionee.ssp.service.third.ThirdAdService;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public class BaseAdFlowModeSelectServiceImpl extends BaseAdFlowServiceImpl{
	 /**
     * 第三方广告服务层
     */
    @Autowired
    ThirdAdService thirdAdService;
    
    /**
     * 竞价广告层
     */
    @Autowired
    BidAdService bidAdService;
    
    /**
     *adx选择服务层 
     */
    @Autowired
    AdxSelectService adxSelectService;
    
    /**
     * 广播服务层
     */
    @Autowired
    BroadCastService broadCastService;
    
    /**
     * 流量分配服务层，安装级别、流量配比等确定流量给谁
     */
    @Autowired
    AdFlowCtrlService adFlowCtrlService;
    
    /**
     * 广告模式过滤服务，某些包名、app_id、adSlot_id只能走单个模式
     */
    @Autowired
    AdFlowModeFilterService adFlowModeFilterService;
    
	
	/**
	 * 广播所有adx时，根据redis配置获取adx列表
	 */
	@Autowired
	AdxListService adxListService;
	
	
    

}
