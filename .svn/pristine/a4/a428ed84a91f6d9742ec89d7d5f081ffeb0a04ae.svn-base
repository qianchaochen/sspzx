package com.gionee.ssp.service.lingji.impl;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.conf.ad.AdConfService;
import com.gionee.ssp.service.lingji.LingjiAdService;
import com.gionee.ssp.service.lingji.LingjiDataService;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.exception.Errors;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

@Service
public class LingjiHttpServiceImpl implements LingjiAdService{
	
	/**
	 * 数据服务层
	 */
	@Autowired
	LingjiDataService lingjiDataService;
	
	/**
	 * Adx信息服务层
	 */
	@Autowired
	AdxInfoService adxInfoService;
	
	/**
	 * 广告位配置管理服务
	 */
	@Autowired
	AdConfService adConfService;
	
	@Override
	public SdkResponseVO getAd(SdkRequestVO req, FillingDataVO vo)
			throws Exception {

		HttpRequestBase get=this.getHttpRequest(req, vo);
		
		JSONObject response = null;
		HttpEntity entity = WKHttpUtils.sendHttp(get);
        
		SdkResponseVO rsp = new SdkResponseVO();
        try {
            if (entity != null) {
            	String strResult = EntityUtils.toString(entity);
				response = new JSONObject(strResult);
            }
        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } 
		return lingjiDataService.rspInfoHandler(response, req);
	}

	/**主流程里面屏蔽调用null作为参数
	 * @param requestVO
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private HttpRequestBase getHttpRequest(SdkRequestVO requestVO,
			FillingDataVO vo) throws Exception{
		AdxInfoVO adx=null;
		return this.getHttpRequest(requestVO, vo, adx);
	}
	
	@Override
	public HttpRequestBase getHttpRequest(SdkRequestVO req,
			FillingDataVO vo,AdxInfoVO adxInfoVO) throws Exception { //AdxInfoVO adxInfoVO请求第三方广告时没有用到
		//确定adx对应的广告位信息
		AdxAdslotInfoVo adx = adxInfoService.getAdxAdslotInfo(req.getApp().getApp_id(), req.getAdslot().getAdslot_id(), vo, AdxType.LINGJI.getAdx());
		
		HttpGet get = lingjiDataService.reqInfoBuilder(req, vo, adx);
		
		return get;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity, FillingDataVO vo,
			SdkRequestVO req) throws Exception {
		JSONObject mobadsResponse = null;
		SdkResponseVO rsp = new SdkResponseVO();
		try {
            if (entity != null) {
            	String strResult = EntityUtils.toString(entity);
				mobadsResponse = new JSONObject(strResult);
            }
        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } 
		return lingjiDataService.rspInfoHandler(mobadsResponse, req);
	}
	
}
