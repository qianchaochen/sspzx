package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertActiveTrackerService;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertActiveTrackerServiceImpl implements ConvertActiveTrackerService{

	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp, Ad ad) {
		List<String> result = new ArrayList<String>();
		List<String> list = ad.getActivetraceurlList();
		if (list != null && list.size() > 0) {
			for (String adxActionUrl : list) {
				result.add(adxActionUrl);
			}
		}
		rsp.setActvtrackers(result);	
	}
}
