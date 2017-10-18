package com.gionee.ssp.service.job.image;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gionee.ssp.service.image.ImageMd5Service;
import com.gionee.ssp.thread.image.ImageUrlQueue;


/**锁屏图片MD5计算并缓存起来的定时线程
 * @author dingyw
 *
 * 2017年9月6日
 */
@Component
public class ImageMd5Job {
	/**
	 * 图片服务层
	 */
	@Autowired
	ImageMd5Service imageMd5Service;

	/**
	 * 循环读取队列中的url 
	 * @throws Exception 
	 */
	@Scheduled(cron = "0/20 * * * * ?") 
	public void start() throws Exception {
		//取类中的静态队列
		String url = ImageUrlQueue.queue.poll();
		if(!StringUtils.isEmpty(url)){
			imageMd5Service.saveImageMd5(url);
		}
	}
}
