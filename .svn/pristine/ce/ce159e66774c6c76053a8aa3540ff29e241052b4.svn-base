package com.gionee.ssp.service.req.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.ConvertReqAppService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkAppVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqAppServiceImpl implements ConvertReqAppService{
	/**
	 * @title: setApp
	 * @description: 转换APP应用字段
	 * @param builder
	 *            ADXRequest对象
	 * @param sdkReq
	 *            SDK请求字段
	 */
	public void setApp(Builder builder, SdkRequestVO sdkReq, FillingDataVO vo) {

		WKSSP.App.Builder appBuilder = WKSSP.App.newBuilder();
		SdkAppVo app = sdkReq.getApp();

		appBuilder.setId(app.getApp_id());
		appBuilder.setName(vo.getApp_name());
		appBuilder.setVer(app.getApp_version());
		appBuilder.setBundle(vo.getBundle());
		String keywords = vo.getKeywords();
		// keywors字段非必填
		if (StringUtils.isNotBlank(keywords)) {

			appBuilder.setKeywords(vo.getKeywords());
		}
		// cat字段非必填
		List<String> catList = vo.getCat();
		if (catList != null) {
			String cat;
			Iterator<String> it_cat = catList.iterator();
			while (it_cat.hasNext()) {
				cat = it_cat.next();
				appBuilder.addCat(cat);
			}
		}

		builder.setApp(appBuilder);
	}

}
