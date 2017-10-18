package com.gionee.ssp.service.adFlow.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public class BaseAdFlowServiceImpl {
	
    /**
     *广告流量模式，0：单个 1：多个
     *单个是指旧模式，发送之前确定ADX，然后只发送一个ADX；
     *多个模式是指，所有的ADX都发,然后根据优先级和流量配比，确定选择哪个广告 
     */
	@Value("#{ad_flow_config.AD_FLOW_MODE}")
    protected String AD_FLOW_MODE;
    
	/**
	 * 发送广播的过期时间
	 */
	@Value("#{ad_flow_config.TIMEOUT}")
    protected long TIMEOUT;

	/**
	 * 请求分发的最大连接数
	 */
	@Value("#{ad_flow_config.MAX_TOTAL}")
    protected int MAX_TOTAL;

    /** 
     * 分发每个路由的最大连接数
     */
	@Value("#{ad_flow_config.DEFAULT_MAX_PER_ROUTE}")
    protected int DEFAULT_MAX_PER_ROUTE;
    
    
	/**
	 * 金立流量等级
	 */
	@Value("#{ad_flow_config.FIRM_FLOW_LEVEL}")
    protected int FIRM_FLOW_LEVEL;
	
    /** 玩咖流量等级 **/
	@Value("#{ad_flow_config.WK_FLOW_LEVEL}")
    protected int WK_FLOW_LEVEL;
	
    /** 百度流量等级 **/
	@Value("#{ad_flow_config.BAIDU_FLOW_LEVEL}")
    protected int BAIDU_FLOW_LEVEL;
	
    /** zarker流量等级 **/
	@Value("#{ad_flow_config.ZAKER_FLOW_LEVEL}")
    protected int ZAKER_FLOW_LEVEL;
	
    /** 头条流量等级 **/
	@Value("#{ad_flow_config.TOUTIAO_FLOW_LEVEL}")
    protected int TOUTIAO_FLOW_LEVEL;
	
    /** inmobi流量等级 **/
	@Value("#{ad_flow_config.INMOBI_FLOW_LEVEL}")
	protected int INMOBI_FLOW_LEVEL;


}
