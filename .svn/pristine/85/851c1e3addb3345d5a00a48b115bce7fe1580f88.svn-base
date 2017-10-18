package com.gionee.ssp.service.adx.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.thread.image.ImageUrlQueue;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.AdType;
import com.wk.ssp.vo.sdk.SdkNativeVO;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public class BaseAdxDataServiceImpl extends BaseAdxServiceImpl{
	
	/**
	 * 图片MD5服务层
	 */
	@Autowired
	ImageMd5Service imageMd5Service;
	
	protected boolean handleMd5(String url,  SdkNativeVO nativeVo){
		//将md5存入set中用于给第三方预加载
		imageMd5Service.saveImageUrlByDate(url);
		
		if(imageMd5Service.isNeedMd5()){
			//需要设置图片地址的md5
			if(StringUtils.isNotEmpty(imageMd5Service.getUrlMd5(url))){
				List<String> imgmd5s = new ArrayList<String>();
				imgmd5s.add(imageMd5Service.getUrlMd5(url));
				nativeVo.setImgmd5(imgmd5s);
				return true;
			}else{
				//缓存没有的话, 开始存md5, 这一次请求不返回
				ImageUrlQueue.addUrl(url);
				return false;
			}
		}
		return true;
	}
	
	protected int getAdType(FillingDataVO fillingDataVO){
		if(fillingDataVO.getNativ() != null && fillingDataVO.getNativ().size() > 0){
			return AdType.NATIVE.getType();
		}else if(0 != fillingDataVO.getIs_splash()){
			return AdType.SPLASH.getType();
		}else if(1 == fillingDataVO.getInstl()){
			return AdType.INSTL.getType();
		}else if(fillingDataVO.getBanner() != null){
			return AdType.BANNER.getType();
		}else{
			return 0;	//未知
		}
	}
}
