package com.gionee.ssp.service.inmobi;


import org.json.JSONObject;

import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;
/**
 * 
 * @description inmobi数据处理
 * @author wuxing
 * @date 2017年4月5日
 *
 */
public interface InmobiDataService {
	
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
	public JSONObject reqInfoBuilder(SdkRequestVO requestVO, String adxAdslotId, String appPackage);
	
	/**
	 * 处理返回信息
	 * @param mobadsResponse
	 * @param inmobiInfoVO
	 * @param requestVO
	 * @return
	 * @throws BaiduApiException
	 * @throws Exception
	 * 
	 * @author: wuxing
	 * @date: 2017年4月7日 上午11:19:48
	 *
	 */
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse, SdkRequestVO requestVO, FillingDataVO fillingDataVO) throws Exception;

}
