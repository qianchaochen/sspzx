package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gionee.ssp.service.rsp.tracker.ConvertInstallTrackerService;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertInstallTrackerServiceImpl implements ConvertInstallTrackerService{

	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp, Ad ad) {
		List<String> result = new ArrayList<>();
		List<String> list = ad.getIntltrackersList();
		if (!CollectionUtils.isEmpty(list)) {
			for (String installUrl : list) {
				result.add(installUrl);
			}
		}
		rsp.setIntltrackers(result);
		
	}

}
