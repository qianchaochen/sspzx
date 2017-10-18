package com.gionee.ssp.service.bid.impl;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.bid.BidAdService;
import com.gionee.ssp.service.gameAndStore.GameAndAppstoreService;
import com.gionee.ssp.service.req.ConvertReqService;
import com.gionee.ssp.service.rsp.ConvertRspService;
import com.wk.model.adx.WKSSP.WKSSPRequest;
import com.wk.model.adx.WKSSP.WKSSPResponse;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**竞价广告服务层
 * 请求到金立的ADX或其它的ADX
 * @author dingyw
 *
 * 2017年4月19日
 */
@Service
public class BidAdServiceImpl implements BidAdService{
    
    /**
     *特殊流程，在软件商店和应用 返回的广告信息中添加上额外信息
     */
    @Autowired
    GameAndAppstoreService gameAndAppstoreService;
    
    /**
     * req转换广告服务层
     */
    @Autowired
    ConvertReqService convertReqService;
    /**
     * rsp转换广告服务层
     */
    @Autowired
    ConvertRspService convertRspService;
    
    
	public SdkResponseVO getAd(AdxInfoVO adx, SdkRequestVO req,FillingDataVO vo) throws Exception{

        HttpRequestBase post=this.getHttpRequest(req, vo, adx);
        
        HttpEntity entity = WKHttpUtils.sendHttp(post);

        return this.getRsp(entity, vo, req);
	}

    /**
     * @title: getHttpPost
     * @description: 获取Post请求实例
     * @return
     */
    private HttpPost getHttpPost(AdxInfoVO adx) {

        // 创建HttpPost实例，访问URL为adxInfoVO中AdxUrl的值
        HttpPost post = WKHttpUtils.getPost(adx.getAdxUrl());
        // 设置Http请求头
        post.addHeader(Constant.SSPID, adx.getSspId());
        post.addHeader(Constant.TOKEN, adx.getToken());
        post.addHeader("Content-Type", "application/x-protobuf");
        post.addHeader("Accept", "application/x-protobuf");

        return post;
    }

	@Override
	public HttpRequestBase getHttpRequest(SdkRequestVO req,FillingDataVO vo,AdxInfoVO adx) throws Exception {
	      // 生成ADX请求对象
        WKSSPRequest request = convertReqService.getReq(req, vo);
        
        HttpPost post = this.getHttpPost(adx);
        
        WKHttpUtils.setEntity(post, request.toByteArray());
        
		return post;
	}

	@Override
	public SdkResponseVO getRsp(HttpEntity entity,
			FillingDataVO vo, SdkRequestVO req)
			throws Exception {
		
		//获取rsp
        WKSSPResponse response = this.getRsp(entity);   
        //处理rsp
        SdkResponseVO rsp =  convertRspService.getSdkRsp(response, vo);
 
        //游戏大厅和软件商店需要特殊处理
        gameAndAppstoreService.dealWithGameHallAndAppStoreAdInfo(rsp, vo);
        
        return rsp;
	}
	
	/**获取rsp
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private WKSSPResponse getRsp(HttpEntity entity) throws Exception{
        WKSSPResponse response = null;
        InputStream inputStream = null;
        try {
            if (entity != null) {
                inputStream = entity.getContent();
                response = WKSSPResponse.parseFrom(inputStream);
            }
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return response;
	}
}
