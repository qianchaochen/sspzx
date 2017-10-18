package com.gionee.ssp.service.image;

import java.util.Set;

import com.gionee.ssp.vo.preload.PreloadImgInfoVo;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ImageMd5Service {
	
	/**是否需要计算MD5，根据redis是否已经有内容判断
	 * @return
	 */
	public boolean isNeedMd5();
	
	/**根据url查询MD5
	 * @param url
	 * @return
	 */
	public String getUrlMd5(String url);
	
	/**根据URL存储MD5
	 * @param url
	 */
	public void saveImageUrlByDate(String url);
	
	/**根据URL存储图片MD5
	 * @param url
	 */
	public void saveImageMd5(String url)throws Exception;
	

	/**获取redis中缓存的锁屏请求过的广告图片URL
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Set<PreloadImgInfoVo> getImageUrlsByDate(String key) throws Exception;

}
