package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertDownLoadFinish_4G_TrackerService;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertDownLoadFinish_4G_TrackerServiceImpl implements ConvertDownLoadFinish_4G_TrackerService{

	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp, Ad ad) {
		List<String> result = new ArrayList<>();
		List<String> list = ad.getDownload4GList();
		if (list != null && list.size() > 0) {
			for (String adxdwnl : list) {
				result.add(adxdwnl);
			}
		}
		rsp.setDwnltrackers_4g(result);
		
	}

}
