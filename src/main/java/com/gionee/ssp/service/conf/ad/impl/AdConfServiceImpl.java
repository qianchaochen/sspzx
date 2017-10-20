package com.gionee.ssp.service.conf.ad.impl;

import com.gionee.ssp.vo.FloatAdConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.conf.ad.AdConfService;
import com.gionee.ssp.service.redis.RedisService;
import com.gionee.ssp.vo.SSPAdShieldConfigVo;
import com.wk.exception.SDKBusinessError;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;


/**广告配置服务层
 * @author dingyw
 *
 * 2017年9月5日
 */
@Service
public class AdConfServiceImpl implements AdConfService {

	/**
	 * redis服务层
	 */
	@Autowired
	RedisService redisService;
	
	@Override
	public FillingDataVO getAdConfData(SdkRequestVO req) throws Exception {
		String appid = req.getApp().getApp_id();
		String adSlotId = req.getAdslot().getAdslot_id();
		String key = Constant.REDIS_KEY + appid + "_" + adSlotId; 
		String json = redisService.query(key);
		if(StringUtils.isBlank(json)){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}
		FillingDataVO result = JsonUtils.readJson2Object(json, FillingDataVO.class);
		return result;
	}
	
	@Override
	public FillingDataVO getAdConfData(String appId,String adId) throws Exception {
		String key = Constant.REDIS_KEY + appId + "_" + adId; 
		String json = redisService.query(key);
		if(StringUtils.isBlank(json)){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}
		return JsonUtils.readJson2Object(json, FillingDataVO.class);
	}

	@Override
	public SSPAdShieldConfigVo getAdShieldCfg(String key) throws Exception {
		String json = redisService.query(key);
		return JsonUtils.readJson2Object(json, SSPAdShieldConfigVo.class);
	}

	@Override
	public FloatAdConfigVo getFloatAdCfg(String key) throws Exception {
		String json = redisService.query(key);
		return JsonUtils.readJson2Object(json, FloatAdConfigVo.class);
	}
}
