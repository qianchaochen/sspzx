package com.gionee.ssp.service.conf.ad.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.conf.ad.PlatAdConfService;
import com.gionee.ssp.service.redis.RedisService;
import com.gionee.ssp.vo.PlatAdConfVo;
import com.wk.exception.SDKBusinessError;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年9月28日
 */
@Service
public class PlatAdConfServiceImpl implements PlatAdConfService{
	/**
	 * redis服务层
	 */
	@Autowired
	RedisService redisService;
	
	@Override
	public PlatAdConfVo getBaiduAdConfVo(String app_id, String ad_id,FillingDataVO vo) throws Exception {
		String key = Constant.REDIS_KEY + app_id + "_" + ad_id + "_"+CommonConstant.SYS_CODE.BAIDU_NAME.getValue(); 
		PlatAdConfVo record= this.getAdConfVo(key);
		
		//如果platAdConfVo为空，则取FillingDataVO vo，然后设置对应的redis的值
		if(null==record){
			//从vo里面获取
			record =vo.getBaidu(); //这里获取的都不一样
		}
		if(null==record){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}else{
			this.setAdConfo(key, JsonUtils.writeObject2Json(record));
		}
		return record;
	}

	@Override
	public PlatAdConfVo getInmobiAdConfVo(String app_id, String ad_id,FillingDataVO vo)throws Exception {
		String key = Constant.REDIS_KEY + app_id + "_" + ad_id + "_"+CommonConstant.SYS_CODE.INMOBI_NAME.getValue(); 
		
		PlatAdConfVo record= this.getAdConfVo(key);
		
		//如果platAdConfVo为空，则取FillingDataVO vo，然后设置对应的redis的值
		if(null==record){
			//从vo里面获取
			record =vo.getInmobi(); //这里获取的都不一样
		}
		if(null==record){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}else{
			this.setAdConfo(key, JsonUtils.writeObject2Json(record));
		}
		return record;
	}
	

	@Override
	public PlatAdConfVo getZakerAdConfVo(String app_id, String ad_id,FillingDataVO vo) throws Exception{
		String key = Constant.REDIS_KEY + app_id + "_" + ad_id + "_"+CommonConstant.SYS_CODE.ZAKER_NAME.getValue(); 

		PlatAdConfVo record= this.getAdConfVo(key);
		
		//如果platAdConfVo为空，则取FillingDataVO vo，然后设置对应的redis的值
		if(null==record){
			//从vo里面获取
			record =vo.getZaker(); //这里获取的都不一样
		}
		if(null==record){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}else{
			this.setAdConfo(key, JsonUtils.writeObject2Json(record));
		}
		return record;
	}

	@Override
	public PlatAdConfVo getLingjiAdConfVo(String app_id, String ad_id,FillingDataVO vo) throws Exception{
		String key = Constant.REDIS_KEY + app_id + "_" + ad_id + "_"+CommonConstant.SYS_CODE.LINGJI_NAME.getValue(); 
		return this.getAdConfVo(key); //灵集FillingDataVO redis还没有值
	}
	@Override
	public PlatAdConfVo getToutiaoAdConfVo(String app_id, String ad_id,FillingDataVO vo)
			throws Exception {
		String key = Constant.REDIS_KEY + app_id + "_" + ad_id + "_"+CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue(); 
		
		PlatAdConfVo record= this.getAdConfVo(key);
		
		//如果platAdConfVo为空，则取FillingDataVO vo，然后设置对应的redis的值
		if(null==record){
			//从vo里面获取
			record =vo.getJrtt(); //这里获取的都不一样
		}
		if(null==record){
			throw new BusinessException(SDKBusinessError.NOT_MATCH_ADSLOT_ID);
		}else{
			this.setAdConfo(key, JsonUtils.writeObject2Json(record));
		}
		return record;
	}
	
	/**获取redis的值
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private PlatAdConfVo getAdConfVo(String key)throws Exception{
		String json = redisService.query(key);
		if(StringUtils.isBlank(json)){
			return null;
		}
		return JsonUtils.readJson2Object(json, PlatAdConfVo.class);
	}
	/**设置redis的值 20170928 by dingyw，等数据从FillingDataVO迁移到各个key后，本函数可以废弃
	 * @param key
	 * @throws Exception
	 */
	private void setAdConfo(String key,String value)throws Exception{
		redisService.save(key, value);
	}


}
