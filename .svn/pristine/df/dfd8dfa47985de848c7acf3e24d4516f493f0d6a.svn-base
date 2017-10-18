package com.gionee.ssp.service.baidu.imp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.ub.api.UbapiClient;
import com.gionee.ssp.model.BaiduMobads.MobadsRequest;
import com.gionee.ssp.model.BaiduMobads.MobadsResponse;
import com.gionee.ssp.service.adx.AdxInfoService;
import com.gionee.ssp.service.baidu.BaiduAdService;
import com.gionee.ssp.service.baidu.BaiduDataService;
import com.gionee.ssp.service.baidu.impl.BaseBaiduServiceImpl;
import com.gionee.ssp.utils.baidu.BaiduPrivateKeyUtil;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.exception.Errors;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2016年12月15日
 */
@Service
public class BaiduAdServiceImpl extends BaseBaiduServiceImpl implements BaiduAdService {
	
	Logger logger = LogManager.getLogger(getClass());
	
	/**
	 * 百度数据服务层
	 */
	@Autowired
	private BaiduDataService baiduDataService;
	
	/**
	 * Adx信息服务层
	 */
	@Autowired
	AdxInfoService adxInfoService;
	/**
	 * 从证书路径解析出来的key
	 */
	private String private_key;
	
    
    private final String CONTENT_TYPE="application/x-protobuf";

	@Override
	public SdkResponseVO getAd(SdkRequestVO requestVO, FillingDataVO fillingDataVO)
			throws Exception {
		
		SdkResponseVO sdkResponseVO = new SdkResponseVO();
		
		HttpRequestBase post = this.getHttpRequest(requestVO, fillingDataVO);

        try {
            HttpEntity entity = WKHttpUtils.sendHttp(post);
            return this.getRsp(entity, fillingDataVO, requestVO);
        } catch(Exception e){
        	sdkResponseVO.setError_code(Errors.NO_CONTENT);
        	sdkResponseVO.setAdms(new ArrayList<>());
        	return sdkResponseVO;
        } 
	}
	
	/**
     * @title: getHttpPost
     * @description: 获取Post请求实例
     * @return
     */
    private HttpPost getHttpPost(MobadsRequest mobadsRequest) {

        // 创建HttpPost实例，访问URL为adxInfoVO中AdxUrl的值
        HttpPost post = WKHttpUtils.getPost(baidu_req_url);
        // 设置Http请求头
        post.addHeader("Content-Type", CONTENT_TYPE);
        post.addHeader("Accept", CONTENT_TYPE);
       
        //设置安全信息
        this.setSafeInfo(post,mobadsRequest);
        
        return post;
    }
    /**设置安全信息
     * @param post
     * @param mobadsRequest
     */
    private void setSafeInfo(HttpPost post,MobadsRequest mobadsRequest){
    	Date now =new Date();
    	//从文件中获取私钥
    	if(StringUtils.isEmpty(private_key)){
    		private_key=BaiduPrivateKeyUtil.readPemFile(BaiduAdServiceImpl.class.getResource(baidu_privateKey_path).getPath());
    	}
    	UbapiClient ubapiClient=new UbapiClient(baidu_accessKey,private_key);
    	post.addHeader("x-ub-date", String.valueOf(now.getTime()));
        post.addHeader("x-ub-authorization", baidu_accessKey + ":" + 
    	ubapiClient.getAuthorization("POST",baidu_req_ad_uri, CONTENT_TYPE, mobadsRequest.toByteArray(), now));
    }
	
    /**主流程里面屏蔽调用null作为参数
     * @param req
     * @param vo
     * @return
     * @throws Exception
     */
    private HttpRequestBase getHttpRequest(SdkRequestVO req,
			FillingDataVO vo) throws Exception{
		AdxInfoVO adxInfoVO=null;
		return this.getHttpRequest(req, vo, adxInfoVO);
	}
	@Override
	public HttpRequestBase getHttpRequest(SdkRequestVO req,
			FillingDataVO vo,AdxInfoVO adxInfoVO) throws Exception {
		//确定adx对应的广告位信息
		AdxAdslotInfoVo adx = adxInfoService.getAdxAdslotInfo(req.getApp().getApp_id(), req.getAdslot().getAdslot_id(), vo, AdxType.BAIDU.getAdx());
		//构建请求信息
		MobadsRequest mobadsRequest = baiduDataService.reqInfoBuilder(req, vo,adx);
        
		HttpPost post = getHttpPost(mobadsRequest);
        WKHttpUtils.setEntity(post, mobadsRequest.toByteArray());
		return post;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity,
			FillingDataVO vo, SdkRequestVO req)
			throws Exception {
		SdkResponseVO rsp = new SdkResponseVO();
		
		MobadsResponse mobadsResponse=null;
        InputStream inputStream = null;
        try {
            if (entity != null) {
                inputStream = entity.getContent();
                mobadsResponse = MobadsResponse.parseFrom(inputStream);
            }
    
           logger.info("BAIDU_RESPONSE \n"+mobadsResponse.toString());

        } catch(Exception e){
        	rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
        } finally{
        	if (null != inputStream) 
        		inputStream.close();
        }
		
		return baiduDataService.rspInfoHandler(mobadsResponse, vo, req);
	}

	
}
