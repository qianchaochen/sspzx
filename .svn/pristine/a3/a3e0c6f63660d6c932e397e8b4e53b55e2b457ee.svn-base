package com.gionee.ssp.service.rsp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.rsp.ConvertRspAdService;
import com.gionee.ssp.service.rsp.ConvertRspAdmService;
import com.gionee.ssp.service.rsp.tracker.ConvertRspTrackerService;
import com.wk.model.adx.WKSSP;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 *         2017年9月7日
 * 
 *         modified by zhengk 106 增加了4g下载开始和下载完成，wifi预约下载开始和下载完成
 */
@Service
public class ConvertRspAdServiceImpl implements ConvertRspAdService {

	/**
	 * 转换广告元服务层
	 */
	@Autowired
	ConvertRspAdmService convertRspAdmService;
	
	/**
	 * 转换监播服务层
	 */
	@Autowired
	ConvertRspTrackerService convertRspTrackerService;

	@Override
	public SdkResponseAdVO covertAd(WKSSP.Ad ad, FillingDataVO vo) throws Exception {

		SdkResponseAdVO rsp = new SdkResponseAdVO();

		// v1.1增加adm元素
		if (ad.hasAdm()) {
			// 转化广告元adm
			convertRspAdmService.convertAdm(rsp, ad.getAdm(), vo);
		} else {
			rsp.setImgurl(ad.getImgurl());
			rsp.setClkurl(ad.getClkurl());
			rsp.setH5_url(ad.getH5Url());
			rsp.setDeep_link(ad.getDeeplink());
			// 纯图片创意
			rsp.setCreative_type(CommonConstant.CreativeType.IMG.getValue());
		}
		// 填充广告位ID
		rsp.setAdslot_id(ad.getAdslotId());

		rsp.setInteraction_type(ad.getInteractionType().getNumber());

		// 若是下载类广告，填充应用包名
		if (StringUtils.isNotBlank(ad.getBundle())) {
			rsp.setBundle(ad.getBundle());
		} else {
			rsp.setBundle("");
		}

		// 填充创意宽
		rsp.setW(ad.getW());
		// 填充创意高
		rsp.setH(ad.getH());

		//转换监播信息
		convertRspTrackerService.convertTrackersInfo(rsp, ad);

		return rsp;
	}

}
