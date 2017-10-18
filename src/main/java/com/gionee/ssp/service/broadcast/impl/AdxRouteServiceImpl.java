package com.gionee.ssp.service.broadcast.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.baidu.BaiduAdService;
import com.gionee.ssp.service.bid.BidAdService;
import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.gionee.ssp.service.broadcast.AdxRouteService;
import com.gionee.ssp.service.inmobi.InmobiAdService;
import com.gionee.ssp.service.lingji.LingjiAdService;
import com.gionee.ssp.service.toutiao.ToutiaoAdService;
import com.gionee.ssp.service.zaker.ZakerAdService;

/**根据ADX的名字，找到对应的service，然后调用对应的服务，组装httpPost或httpGet，然后对返回报文进行不同的解析
 * @author dingyw
 *
 * 2017年4月21日
 */
@Service
public class AdxRouteServiceImpl implements AdxRouteService{
	
	/**
	 * 竞价访问类
	 */
	@Autowired
	BidAdService bidAdHttpService;
	
	/**
	 * 百度访问类
	 */
	@Autowired
	BaiduAdService baiduAdService;
	
	/**
	 * 头条访问类
	 */
	@Autowired
	ToutiaoAdService toutiaoAdService;
	
	/**
	 * Zaker访问类
	 */
	@Autowired
	ZakerAdService zakerAdService;
	
	/**
	 * inmobi访问类
	 */
	@Autowired
	InmobiAdService inmobiAdService;
	
	/**
	 * 灵集访问类
	 */
	@Autowired
	LingjiAdService lingjiAdService;

	@Override
	public AdBroadcastService getAdBroadcastHttpService(String adx_name) {

		if(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue().equals(adx_name)){
			return toutiaoAdService;
		}
		if(CommonConstant.SYS_CODE.ZAKER_NAME.getValue().equals(adx_name)){
			return zakerAdService;
		}
		if(CommonConstant.SYS_CODE.INMOBI_NAME.getValue().equals(adx_name)){
			return inmobiAdService;
		}
		if(CommonConstant.SYS_CODE.LINGJI_NAME.getValue().equals(adx_name)){
			return lingjiAdService;
		}
		if(CommonConstant.SYS_CODE.BAIDU_NAME.getValue().equals(adx_name)){
			return baiduAdService;
		}
			return bidAdHttpService;
	}

}
