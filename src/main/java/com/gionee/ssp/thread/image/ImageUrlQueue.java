package com.gionee.ssp.thread.image;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public class ImageUrlQueue {
	
	//应用把图片URL放入队列,线程每隔一段时间读取队列，然后算好图片md5 放入到redis
	public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	public ConcurrentLinkedQueue<String> getQueue() {
		return queue;
	}

	public void setQueue(ConcurrentLinkedQueue<String> queue) {
		ImageUrlQueue.queue = queue;
	}
	
	public static void addUrl(String url) {
		ImageUrlQueue.queue.add(url);
	}

	public static void addUrlList(List<String> urlList) {
		ImageUrlQueue.queue.addAll(urlList);
	}


}
