package com.gionee.ssp.service.preload.lock.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.cp.impl.BaseCpServiceImpl;
import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.service.preload.lock.PreloadEsSearchService;
import com.gionee.ssp.service.preload.lock.PreloadService;
import com.gionee.ssp.thread.image.ImageUrlQueue;
import com.gionee.ssp.vo.preload.PreloadImgInfoVo;
import com.gionee.ssp.vo.preload.PreloadResponseVo;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeVO;
import com.wk.ssp.utils.StringUtils;

/**
 * 
 * @ClassName PreloadServiceImpl
 * @Desc { 锁屏预加载服务}
 * @author zhengk
 * @date Apr 14, 2017
 */

@Service
public class PreloadServiceImpl extends BaseCpServiceImpl implements PreloadService {

	/**
	 * 广告搜索服务层
	 */
	@Autowired
	PreloadEsSearchService esSearchService;
	
	/**
	 * 图片MD5服务层
	 */
	@Autowired
	ImageMd5Service imageMd5Service;

	@Override
	public PreloadResponseVo getPreloadData() throws Exception {

		PreloadResponseVo rsp = new PreloadResponseVo();
		rsp.setCode(Errors.NO_ERROR + "");
		rsp.setMsg("");

		Set<PreloadImgInfoVo> materials = new HashSet<PreloadImgInfoVo>();

		// ES搜索锁屏开屏素材
		List<CampaignVO> campaignVOList = esSearchService.search(lock_adslotId);

		if (!CollectionUtils.isEmpty(campaignVOList)) {
			for (CampaignVO campaignVO : campaignVOList) {
				PreloadImgInfoVo img = new PreloadImgInfoVo();
				if (campaignVO.getCreatives() != null && campaignVO.getCreatives().length != 0) {
					for (CreativeVO creative : campaignVO.getCreatives()) {
						img.setImg_url(creative.getImg_url());
						materials.add(img);
					}
				}
			}
		}

		// 获取REDIS存放的图片URL
		// 获取时间20170418 20170417 20170416 .....

		// 获取过去三天锁屏请求到的URLs
		for (int i = 1; i <= 3; i++) {
			Set<PreloadImgInfoVo> urlSet = imageMd5Service.getImageUrlsByDate(getPastDate(i));
			if (!CollectionUtils.isEmpty(urlSet)) {
				materials.addAll(urlSet);
			}
		}

		if (CollectionUtils.isEmpty(materials)) {
			rsp.setCode(Errors.NO_CONTENT + "");
			rsp.setMsg(String.format("广告位%s无直投素材&无已经请求过的广告素材", lock_adslotId));
			return rsp;
		}

		// 根据开屏图片URL获取图片MD5
		boolean is_md5_ok = true;
		Set<String> needMd5Urls = new HashSet<>();

		List<PreloadImgInfoVo> list = new ArrayList<>();
		list.addAll(materials);
		int size = list.size();
		// 是否需要计算MD5
		if (imageMd5Service.isNeedMd5()) {
			for (int index = 0; index < size; index++) {
				String imgUrl = list.get(index).getImg_url();
				String md5 = imageMd5Service.getUrlMd5(imgUrl);
				// 如果redis中不存在该url的md5值
				if (StringUtils.isBlank(md5)) {
					// 调用MD5方法计算该图片的MD5值
					needMd5Urls.add(imgUrl);
					is_md5_ok = false;
				} else {
					list.get(index).setImg_md5(md5);
				}
			}
		}

		if (!is_md5_ok) {
			// 计算MD5去
			ImageUrlQueue.addUrlList(new ArrayList<>(needMd5Urls));
			rsp.setCode(Errors.NO_CONTENT + "");
			rsp.setMsg("图片MD5值还未计算完成，请10分钟后尝试");
		} else {
			rsp.setDatas(list);
		}

		return rsp;
	}

	/**
	 * 获取N天以前日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	private static String getPastDate(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(calendar.getTime());
	}
}
