package com.gionee.ssp.service.broadcast;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpRequestBase;

import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**广播SSP请求到所有的ADX，需要提取公共的操作
 * @author dingyw
 *
 * 2017年4月21日
 */
public interface AdBroadcastService {
	

	/**组装httpRequest,可以是httpGet,也可以是httpPost
	 * AdxInfoVO adxInfoVO参数只有在请求竞价类广告有效
	 * @return
	 */
	public HttpRequestBase getHttpRequest(SdkRequestVO requestVO, FillingDataVO fillingDataVO,AdxInfoVO adxInfoVO)throws Exception;
	
	/**根据返回组装广告返回报文,用于广播请求时，统一处理返回报文
	 * @param entity
	 * @return
	 */
	public SdkResponseVO getRsp(HttpEntity entity,FillingDataVO fillingDataVO,SdkRequestVO requestVO)throws Exception;

}
