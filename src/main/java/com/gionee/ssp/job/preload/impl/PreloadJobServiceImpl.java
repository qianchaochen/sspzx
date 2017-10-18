package com.gionee.ssp.job.preload.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gionee.ssp.job.preload.PreloadJobService;
import com.gionee.ssp.service.preload.lock.PreloadService;

/**
 * @ClassName PreloadJob
 * @Desc {预加载任务}
 * @author zhengk
 * @date Apr 17, 2017
 */

@Service
@EnableScheduling
public class PreloadJobServiceImpl implements PreloadJobService {

	@Autowired
	PreloadService preloadService;

	@Scheduled(cron = "0 0 1 * * ? ") // 每天凌晨1点执行一次
	// @Scheduled(cron = "0 0/1 * * * ? ") // 每分钟执行一次
	// @Scheduled(fixedRate = 1000*2)     //固定频率
	@Override
	public void execute() throws Exception {
		preloadService.getPreloadData();
	}
}
