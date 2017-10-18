package com.gionee.ssp.service.zaker;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description 
 * @author wuxing
 * @date 2017年4月5日
 *
 */
public interface ZakerDataService {
	
	/**
	 * 组装请求
	 * @param requestVO
	 * @return
	 * @throws Exception
	 * 
	 * @author: wuxing
	 * @date: 2017年4月7日 上午11:12:32
	 *
	 */
	public HttpGet reqInfoBuilder(SdkRequestVO requestVO, String adxAdslotId)throws Exception;
	
	/**
	 * 处理返回信息
	 * @param mobadsResponse
	 * @param zakerInfoVO
	 * @param requestVO
	 * @return
	 * @throws BaiduApiException
	 * @throws Exception
	 * 
	 * @author: wuxing
	 * @date: 2017年4月7日 上午11:12:20
	 *
	 */
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse, SdkRequestVO requestVO) throws BaiduApiException, Exception;

}
