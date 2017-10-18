package com.gionee.ssp.service.toutiao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.adx.impl.BaseAdxServiceImpl;
import com.gionee.ssp.service.toutiao.ToutiaoAdService;
import com.gionee.ssp.service.toutiao.ToutiaoDataService;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidResponse;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description 
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class ToutiaoAdServiceImpl extends BaseAdxServiceImpl implements ToutiaoAdService {
	
	/**
	 * 头条数据服务层
	 */
	@Autowired
	ToutiaoDataService toutiaodataservice;
	
	
	/**
	 * Adx信息服务层
	 */
	@Autowired
	AdxInfoService adxInfoService;
	
    private final String CONTENT_TYPE="application/x-protobuf";

	@Override
	public SdkResponseVO getAd(SdkRequestVO req, FillingDataVO vo)
			throws Exception {
		
		SdkResponseVO rsp = new SdkResponseVO();
		
		//获取httpPost,实质是一个HttpPost
		HttpRequestBase post=this.getHttpRequest(req, vo);

        try{
        	HttpEntity entity = WKHttpUtils.sendHttp(post);
        	return this.getRsp(entity, vo, req);
        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } 
	}
	
	/**
     * @title: getHttpPost
     * @description: 获取Post请求实例
     * @return
	 * @throws IOException 
     */
    private HttpPost getHttpPost(BidRequest mobadsRequest) throws IOException {
    	
        // 创建HttpPost实例，访问URL为adxInfoVO中AdxUrl的值
        HttpPost post =new HttpPost(toutiao_req_url); //"http://ad.toutiao.com/lb/api/test"
        // 设置Http请求头
        post.addHeader("Content-Type", CONTENT_TYPE);
        post.addHeader("Accept", CONTENT_TYPE);
        
        return post;
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
	public HttpRequestBase getHttpRequest(SdkRequestVO req, FillingDataVO vo,AdxInfoVO adx) throws Exception {
		//确定adx对应的广告位信息
		AdxAdslotInfoVo adxAdslotInfoVo = adxInfoService.getAdxAdslotInfo(req.getApp().getApp_id(), req.getAdslot().getAdslot_id(), vo, AdxType.TOUTIAO.getAdx());
		
		BidRequest request = toutiaodataservice.reqInfoBuilder(req, vo, adxAdslotInfoVo);
		
        HttpPost post = getHttpPost(request);

        WKHttpUtils.setEntity(post, request.toByteArray());
        
        return post;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity,FillingDataVO vo,SdkRequestVO req)throws Exception {
		SdkResponseVO rsp = new SdkResponseVO();
		BidResponse response = null;
        InputStream inputStream = null;
		try {
            if (entity != null) {
                inputStream = entity.getContent();
                response =  BidResponse.parseFrom(inputStream);
            }
        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } finally{
        	if (null != inputStream) 
        		inputStream.close();
        }
		
		return toutiaodataservice.rspInfoHandler(response, vo, req);
	}
}
