package com.gionee.ssp.service.ad.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.ad.AdMd5RspService;
import com.gionee.ssp.service.cp.CheckCpService;
import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.thread.image.ImageUrlQueue;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月28日
 */
@Service
public class AdMd5RspServiceImpl implements AdMd5RspService{

	/**
	 * cp校验服务层
	 */
	@Autowired
	CheckCpService checkCpService;

	/**
	 * 图片MD5服务层
	 */
	@Autowired
	ImageMd5Service imageMd5Service;
	/**
	 * 设置图片的Md5
	 * @param req
	 * @param rsp
	 */
	@Override
	public void setImageMd5(SdkRequestVO req, SdkResponseVO rsp) {

		String app_id = req.getApp().getApp_id();
		String adSlot_id = req.getAdslot().getAdslot_id();

		if (rsp != null && checkCpService.isLock(app_id, adSlot_id)) {
			for (SdkResponseAdVO vo : rsp.getAdms()) {
				SdkNativeVO item = vo.getNativ();
				// 将md5存入set中用于给第三方预加载
				if (item == null) {
					continue;
				}
				List<String> list = new ArrayList<String>();
				Iterator<String> iter = item.getImgurl().iterator();
				while (iter.hasNext()) {
					String url = iter.next();
					// 按天把图片URL放入redis
					imageMd5Service.saveImageUrlByDate(url);

					if (imageMd5Service.isNeedMd5()) {
						// 需要设置图片地址的md5
						String imageMd5 = imageMd5Service.getUrlMd5(url);
						if (StringUtils.isNotEmpty(imageMd5)) {
							list.add(imageMd5);
						} else {
							// 缓存没有的话, 开始存md5, 这一次请求不返回
							ImageUrlQueue.addUrl(url);
							iter.remove();
						}
					}
				}
				item.setImgmd5(list);
			}
		}
	}

}
