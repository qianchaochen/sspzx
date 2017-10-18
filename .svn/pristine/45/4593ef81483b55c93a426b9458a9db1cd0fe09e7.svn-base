package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertDownLoadStart_wifi_TrackerService;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertDownLoadStart_wifi_TrackerServiceImpl implements ConvertDownLoadStart_wifi_TrackerService{

	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp, Ad ad) {
		List<String> result = new ArrayList<>();
		List<String> list = ad.getDwnlstWifiOrderList();
		if (list != null && list.size() > 0) {
			for (String adxOrderStart : list) {
				result.add(adxOrderStart);
			}
		}
		rsp.setDwnlst_order(result);
		
	}

}
