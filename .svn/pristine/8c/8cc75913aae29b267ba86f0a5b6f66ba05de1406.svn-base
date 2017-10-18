package com.gionee.ssp.service.zaker.impl;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.zaker.ZakerAdService;
import com.gionee.ssp.service.zaker.ZakerDataService;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.exception.Errors;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description zaker请求类
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class ZakerAdServiceImpl implements ZakerAdService {
	
	/**
	 *zaker数据服务层 
	 */
	@Autowired
	ZakerDataService zakerDataService;
	
	/**
	 * Adx信息服务层
	 */
	@Autowired
	AdxInfoService adxInfoService;

	@Override
	public SdkResponseVO getAd(SdkRequestVO requestVO, FillingDataVO fillingDataVO)
			throws Exception {

		HttpRequestBase get=this.getHttpRequest(requestVO, fillingDataVO);
		
		JSONObject mobadsResponse = null;
		HttpEntity entity = WKHttpUtils.sendHttp(get);
        
		SdkResponseVO sdkResponseVO = new SdkResponseVO();
        try {
            if (entity != null) {
            	String strResult = EntityUtils.toString(entity);
				mobadsResponse = new JSONObject(strResult);
            }
        } catch(Exception e){
        	sdkResponseVO.setError_code(Errors.NO_CONTENT);
        	sdkResponseVO.setAdms(new ArrayList<>());
        	return sdkResponseVO;
        } 
		return zakerDataService.rspInfoHandler(mobadsResponse, requestVO);
	}

	/**主流程里面屏蔽调用null作为参数
	 * @param requestVO
	 * @param fillingDataVO
	 * @return
	 * @throws Exception
	 */
	private HttpRequestBase getHttpRequest(SdkRequestVO requestVO,
			FillingDataVO fillingDataVO) throws Exception{
		AdxInfoVO adxInfoVO=null;
		return this.getHttpRequest(requestVO, fillingDataVO, adxInfoVO);
	}
	
	@Override
	public HttpRequestBase getHttpRequest(SdkRequestVO req,
			FillingDataVO vo,AdxInfoVO adxInfoVO) throws Exception { //AdxInfoVO adxInfoVO请求第三方广告时没有用到
		//确定adx对应的广告位信息
		AdxAdslotInfoVo adx = adxInfoService.getAdxAdslotInfo(req.getApp().getApp_id(), req.getAdslot().getAdslot_id(), vo, AdxType.ZAKER.getAdx());
		
		HttpGet get = zakerDataService.reqInfoBuilder(req, adx.getAdslotId());
		return get;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity, FillingDataVO fillingDataVO,
			SdkRequestVO requestVO) throws Exception {
		JSONObject mobadsResponse = null;
		SdkResponseVO sdkResponseVO = new SdkResponseVO();
		 try {
	            if (entity != null) {
	            	String strResult = EntityUtils.toString(entity);
					mobadsResponse = new JSONObject(strResult);
	            }
	        } catch(Exception e){
	        	sdkResponseVO.setError_code(Errors.NO_CONTENT);
	        	sdkResponseVO.setAdms(new ArrayList<>());
	        	return sdkResponseVO;
	        } 
	return zakerDataService.rspInfoHandler(mobadsResponse, requestVO);
	}
}
