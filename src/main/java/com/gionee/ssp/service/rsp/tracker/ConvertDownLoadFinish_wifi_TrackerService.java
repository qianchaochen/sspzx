package com.gionee.ssp.service.rsp.tracker;

import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
public interface ConvertDownLoadFinish_wifi_TrackerService {
	
	/**
	 * @param rsp
	 * @param ad
	 */
	public void convertTrackerInfo(SdkResponseAdVO rsp,WKSSP.Ad ad);

}
