package com.gionee.ssp.service.adx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.conf.ad.PlatAdConfService;
import com.gionee.ssp.service.cp.CheckCpService;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.gionee.ssp.vo.PlatAdConfVo;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;


/**adx信息服务层
 * @author dingyw
 *
 * 2017年9月6日
 */
@Service
public class AdxInfoServiceImpl implements AdxInfoService{
	
	/**
	 * 锁屏的包名
	 */
	@Value("#{adx_config.lock_package}")
	private String lock_package;
	/**
	 * 校验cp服务层
	 */
	@Autowired
	CheckCpService checkCpService;
	
	
	/**
	 * ad广告配置服务层
	 */
	@Autowired
	PlatAdConfService platAdConfService;
	
	@Override
	public AdxAdslotInfoVo getAdxAdslotInfo(String app_id, String ad_id, FillingDataVO vo, int cp) throws Exception{
		
		PlatAdConfVo record=new PlatAdConfVo();
		if(AdxType.TOUTIAO.getAdx() == cp){
			record =platAdConfService.getToutiaoAdConfVo(app_id, ad_id,vo);
		}else if(AdxType.INMOBI.getAdx() == cp){
			record =platAdConfService.getInmobiAdConfVo(app_id, ad_id,vo);
		}else if(AdxType.ZAKER.getAdx() == cp){
			record =platAdConfService.getZakerAdConfVo(app_id, ad_id,vo);
		}else if(AdxType.BAIDU.getAdx() == cp){
			record =platAdConfService.getBaiduAdConfVo(app_id, ad_id,vo);
		}else if(AdxType.LINGJI.getAdx() == cp){
			record =platAdConfService.getLingjiAdConfVo(app_id, ad_id,vo);
		}
		
		//获取包名
		String appPackage=this.getPackage(app_id, ad_id, vo);
		return new AdxAdslotInfoVo(record.getAppId(), record.getAdId(), appPackage);
	}
	private String getPackage(String app_id, String ad_id, FillingDataVO vo){
		if(checkCpService.isLock(app_id, ad_id)){
			//第三方应用ID, 广告位ID, 包名
			return lock_package;
		}else{
			return vo.getBundle();
		}
	}
}
