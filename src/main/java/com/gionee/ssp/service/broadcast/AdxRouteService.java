package com.gionee.ssp.service.broadcast;

import com.gionee.ssp.service.broadcast.AdBroadcastService;

/**
 * @author dingyw
 *
 * 2017年4月21日
 */
public interface AdxRouteService {
	
	/**根据ADX的名字，找到对应的service，然后调用对应的服务，组装httpPost或httpGet，然后对返回报文进行不同的解析
	 * @param adx_name
	 * @return
	 */
	public AdBroadcastService getAdBroadcastHttpService(String adx_name);
	
	

}
