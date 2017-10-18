package com.gionee.ssp.service.lingji;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

public interface LingjiDataService {
	/**
	 * 组装请求信息
	 * @param requestVO
	 * @param fillingDataVO
	 * @return
	 * 
	 * @author: wuxing
	 * @date: 2017年4月7日 上午11:19:31
	 *
	 */
	public HttpGet reqInfoBuilder(SdkRequestVO vo, FillingDataVO fillVo, AdxAdslotInfoVo adxAdslotInfoVo) throws Exception;
	
	/**
	 * 处理返回信息
	 * @param mobadsResponse
	 * @param requestVO
	 * @return
	 * @throws Exception
	 * 
	 * @author: wuxing
	 * @date: 2017年4月7日 上午11:19:48
	 *
	 */
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse, SdkRequestVO requestVO) throws Exception;

}
