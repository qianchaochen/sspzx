package com.gionee.ssp.service.baidu;

import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.gionee.ssp.model.BaiduMobads.MobadsRequest;
import com.gionee.ssp.model.BaiduMobads.MobadsResponse;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 百度数据处理服务
 * @author niexiong
 *
 */
public interface BaiduDataService {
	
	/**
	 * 百度请求信息构建
	 * @param requestVO
	 * 			sdk请求信息
	 * @param fillingDataVO
	 * 			redis缓存信息
	 * @return
	 */
	public MobadsRequest reqInfoBuilder(SdkRequestVO requestVO, FillingDataVO fillingDataVO,AdxAdslotInfoVo adx);
	
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
	public SdkResponseVO rspInfoHandler(MobadsResponse mobadsResponse, FillingDataVO fillingDataVO, SdkRequestVO requestVO) throws BaiduApiException;

}
