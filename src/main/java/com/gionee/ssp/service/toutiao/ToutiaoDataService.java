package com.gionee.ssp.service.toutiao;

import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidResponse;
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
public interface ToutiaoDataService {
	
	/**
	 * 百度请求信息构建
	 * @param requestVO
	 * 			sdk请求信息
	 * @param fillingDataVO
	 * 			redis缓存信息
	 * @return
	 */
	public BidRequest reqInfoBuilder(SdkRequestVO requestVO, FillingDataVO fillingDataVO, AdxAdslotInfoVo adxInfoVo);
	
	/**
	 * 返回信息格式化
	 * @param mobadsResponse
	 * 			百度返回信息
	 * @param fillingDataVO
	 * 			redis缓存信息
	 * @param requestVO
	 * 			sdk请求信息
	 * @return
	 * @throws BaiduApiException
	 */
	public SdkResponseVO rspInfoHandler(BidResponse mobadsResponse, FillingDataVO fillingDataVO, SdkRequestVO requestVO) throws Exception;

}
