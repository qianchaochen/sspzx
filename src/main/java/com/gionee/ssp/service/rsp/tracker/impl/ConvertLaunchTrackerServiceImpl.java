package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertLaunchTrackerService;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertLaunchTrackerServiceImpl implements ConvertLaunchTrackerService{

	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp, Ad ad) {
		List<String> result = new ArrayList<>();
		List<String> list = ad.getLaunchtrackersList();
		if (list != null && list.size() > 0) {
			for (String launchUrl : list) {
				result.add(launchUrl);
			}
		}
		rsp.setLaunchtrackers(result);
		
	}

}
