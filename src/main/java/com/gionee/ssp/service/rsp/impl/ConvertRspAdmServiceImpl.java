package com.gionee.ssp.service.rsp.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.service.rsp.ConvertRspAdmService;
import com.wk.model.adx.AdmVO;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillBannerVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkMixVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**广告元服务层
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertRspAdmServiceImpl implements ConvertRspAdmService{
	
	/**
	 * 图片MD5服务层
	 */
	@Autowired
	ImageMd5Service imageMd5Service;
	
	public void convertAdm(SdkResponseAdVO ad, String adm, FillingDataVO vo) throws Exception {

		// adm广告元对象
		AdmVO admVO = JsonUtils.readJson2Object(adm, AdmVO.class);
		String imgurl = ""; // 单个创意图片url
		String icon = ""; // 横幅图文icon url
		List<String> imgurls = admVO.getImgurl(); // adm图片创意url列表
		int material_type = admVO.getItemType();
		icon = admVO.getIcon();

		// 非空判断，不信任上游系统数据
		if (null == imgurls) {
			imgurls = new ArrayList<String>();
		} else if (imgurls.size() > 0) {
			imgurl = imgurls.get(0);
		}

		// 根据广告模板差异判断创意类型
		if (CommonConstant.MATERIAL_TYPE.IMG_TEXT.getValue() == material_type) {
			FillBannerVO fillBannerVO = vo.getBanner();
			SdkMixVO mixVO = new SdkMixVO();
			// 图文创意
			ad.setCreative_type(CommonConstant.CreativeType.MIX.getValue());
			mixVO.setTitle(admVO.getTitle()); // 标题
			mixVO.setSub_title(admVO.getSubtitle()); // 子标题
			mixVO.setImgurl(icon); // 图标url

			// 行为图标
			String actionIcon = admVO.getIconaction();
			if (ObjectUtils.isEmpty(actionIcon)) {
				mixVO.setAcimgurl("");
			} else {
				mixVO.setAcimgurl(actionIcon);
			}
			mixVO.setBg_color(fillBannerVO.getBg_color()); // 背景色值
			mixVO.setText_color(fillBannerVO.getTx_color()); // 文字色值

			ad.setMix(mixVO);

		} else if (CommonConstant.MATERIAL_TYPE.ONLY_IMG.getValue() == material_type) {
			// 纯图片创意
			ad.setCreative_type(CommonConstant.CreativeType.IMG.getValue());
			ad.setImgurl(imgurl);
		} else if ("1,2,3".indexOf(String.valueOf(material_type)) > -1) {

			SdkNativeVO sdkNativeVO = new SdkNativeVO();
			// 原生广告
			ad.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
			sdkNativeVO.setTitle(admVO.getTitle()); // 标题
			sdkNativeVO.setSub_title(admVO.getSubtitle()); // 子标题
			if (StringUtils.isEmpty(admVO.getSource())) {
                sdkNativeVO.setSource(""); // 推广告
            } else {
			sdkNativeVO.setSource(admVO.getSource()); // 推广告
            }
			sdkNativeVO.setImgurl(imgurls); // 创意图片
			if(imageMd5Service.isNeedMd5()){
				sdkNativeVO.setImgmd5(admVO.getImgmd5());
			}
			// 填充原生广告类型 modify by dingyw
			if(CommonConstant.MATERIAL_TYPE.NATIVE_GROUP.getValue() == material_type){
				sdkNativeVO.setType(CommonConstant.MATERIAL_TYPE.NATIVE_GROUP.getValue());
			}else if(CommonConstant.MATERIAL_TYPE.NATIVE_SMALL.getValue() == material_type){
				sdkNativeVO.setType(CommonConstant.MATERIAL_TYPE.NATIVE_SMALL.getValue());
			}else if(CommonConstant.MATERIAL_TYPE.NATIVE_BIG.getValue() == material_type){
				sdkNativeVO.setType(CommonConstant.MATERIAL_TYPE.NATIVE_BIG.getValue());
			}else{
				sdkNativeVO.setType(CommonConstant.MATERIAL_TYPE.NATIVE_SMALL.getValue());
			}

			ad.setNativ(sdkNativeVO);

		} else {
			// 纯图片创意
			ad.setCreative_type(CommonConstant.CreativeType.NO_TYPE.getValue());
			ad.setImgurl(imgurl);
		}
		ad.setClkurl(admVO.getLandingurl()); // 落地页
		
		ad.setH5_url(admVO.getLandingh5());
		
		ad.setDeep_link(admVO.getDeeplink());

		ad.setAd_cp(AdxType.GIONEE.getAdx());

	}

}
