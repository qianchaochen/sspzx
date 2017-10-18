package com.gionee.ssp.service.inmobi.impl;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.adx.impl.BaseAdxServiceImpl;
import com.gionee.ssp.service.inmobi.InmobiAdService;
import com.gionee.ssp.service.inmobi.InmobiDataService;
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
 * @description inmobi传输层
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class InmobiAdServiceImpl extends BaseAdxServiceImpl implements InmobiAdService {
	
	/**
	 * 头条数据服务层
	 */
	@Autowired
	InmobiDataService inmobidataservice;
	
	/**
	 * Adx信息服务层
	 */
	@Autowired
	AdxInfoService adxInfoService;
	
    private final String CONTENT_TYPE="application/json";

	@Override
	public SdkResponseVO getAd(SdkRequestVO requestVO, FillingDataVO fillingDataVO)
			throws Exception {
		SdkResponseVO sdkResponseVO = new SdkResponseVO();

		HttpRequestBase post=this.getHttpRequest(requestVO, fillingDataVO);
		
		if(null==post){
			sdkResponseVO.setError_code(Errors.NO_CONTENT);
        	sdkResponseVO.setAdms(new ArrayList<>());
        	return sdkResponseVO;
		}
     
        try{
        	 HttpEntity entity = WKHttpUtils.sendHttp(post);
        	 return this.getRsp(entity, fillingDataVO, requestVO);
        	
        }catch(Exception e){
        	sdkResponseVO.setError_code(Errors.NO_CONTENT);
        	sdkResponseVO.setAdms(new ArrayList<>());
        	return sdkResponseVO;
        } 
	}
	
	/**
     * @title: getHttpPost
     * @description: 获取Post请求实例
     * @return
	 * @throws Exception 
     */
    private HttpPost getHttpPost(JSONObject mobadsRequest) throws Exception {
        // 创建HttpPost实例，访问URL为adxInfoVO中AdxUrl的
    	HttpPost post = new HttpPost(inmobi_req_url);
        StringEntity se = new StringEntity(mobadsRequest.toString());
		//将entity加入到post中
        post.setEntity(se);
        // 设置Http请求头
        post.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE);
        post.addHeader("Accept", CONTENT_TYPE);
       
        //设置安全信息
       // this.setSafeInfo(post,mobadsRequest);
        
        return post;
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
	public HttpRequestBase getHttpRequest(SdkRequestVO req, FillingDataVO vo, AdxInfoVO adxInfoVO) throws Exception {
		//确定adx对应的广告位信息
		AdxAdslotInfoVo adx = adxInfoService.getAdxAdslotInfo(req.getApp().getApp_id(), req.getAdslot().getAdslot_id(), vo, AdxType.INMOBI.getAdx());
		
		JSONObject mobadsRequest = inmobidataservice.reqInfoBuilder(req, adx.getAdslotId(), adx.getAppPackage());
		
		if(mobadsRequest == null){
			return null;
		}
        HttpPost post = getHttpPost(mobadsRequest);
        return post;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity, FillingDataVO vo,
			SdkRequestVO req) throws Exception {
		SdkResponseVO rsp = new SdkResponseVO();
		JSONObject mobadsResponse = null;
		try {
            if (entity != null) {
            	String strResult = EntityUtils.toString(entity);
            	if(strResult.equals("")){
            		rsp.setError_code(Errors.NO_CONTENT);
                	rsp.setAdms(new ArrayList<>());
                	return rsp;
				}
    	        mobadsResponse = new JSONObject(strResult);
            }
        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } finally{
        	
        }
		return inmobidataservice.rspInfoHandler(mobadsResponse, req, vo);
	}
}
