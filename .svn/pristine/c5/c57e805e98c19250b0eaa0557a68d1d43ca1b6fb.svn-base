package com.gionee.ssp.service.image.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gionee.ssp.sao.redis.SspRedisSao;
import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.utils.image.ImageMd5Utils;
import com.gionee.ssp.vo.preload.PreloadImgInfoVo;
import com.wk.ssp.mvc.Constant;


/**图片md5业务层
 * @author dingyw
 *
 * 2017年9月6日
 */
@Service
public class ImageMd5ServiceImpl implements ImageMd5Service{
	
	Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * redis服务层
	 */
	@Autowired
	SspRedisSao sspRedisSao;
	
	public static final String MD5_SWITCH_KEY = "md5_switch";
	public static final String MD5_SWITCH_ON = "on";

	public boolean isNeedMd5() {
		String result = null;
		try {
			result = sspRedisSao.getObject(MD5_SWITCH_KEY);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return StringUtils.isEmpty(result) ? true : result.equalsIgnoreCase(MD5_SWITCH_ON);
	}

	/**根据url查询MD5
	 * @param url
	 * @return
	 */
	public String getUrlMd5(String url) {
		return sspRedisSao.getObject(url);
	}

	/**根据图片的URL，算出MD5，然后存入到redis中
	 * @param url
	 * @throws Exception
	 */
	public void saveImageMd5(String url) throws Exception {
		String md5 = sspRedisSao.getObject(url);
		if (StringUtils.isEmpty(md5)) {
			return;
		}
		md5 = ImageMd5Utils.getImageMd5(url);
		
		sspRedisSao.putObject(url, md5);
	}
	
	/**按天作为key，保存图片md5
	 * @param url
	 */
	public void saveImageUrlByDate(String url) {
		if (StringUtils.isEmpty(url)) {
			return;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String usedUrlsKey = Constant.REDIS_STORY_LOCKER_USED_URLS + sf.format(new Date());
		sspRedisSao.putList(usedUrlsKey, url);
	}
	
	@Override
	public Set<PreloadImgInfoVo> getImageUrlsByDate(String date) throws Exception {
		String redisKey = Constant.REDIS_STORY_LOCKER_USED_URLS+date;
		//查找对应的value
		List<String> list=sspRedisSao.getList(redisKey);
		Set<PreloadImgInfoVo> result= new HashSet<PreloadImgInfoVo>();
		
		if(list == null ||list.size()==0){
			return result;
		}
		for(int i=0;i<list.size();i++){
			PreloadImgInfoVo item =new PreloadImgInfoVo();
			item.setImg_url(list.get(i));
			result.add(item);
		}
		return result;
	}
}
