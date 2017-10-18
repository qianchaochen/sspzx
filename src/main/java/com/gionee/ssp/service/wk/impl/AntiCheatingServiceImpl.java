package com.gionee.ssp.service.wk.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.antiCheating.AntiCheatingContent;
import com.gionee.ssp.service.wk.AntiCheatingService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.DateTimeUtils;

/**
 * @description: 防作弊策略
 */
@Service
public class AntiCheatingServiceImpl implements AntiCheatingService {
	
	/**
	 * 防刷策略，每秒允许访问次数
	 */
	@Value("#{anti_cheating_config.LIMIT_QPS}")
	private int access_limit;

	@Override
	public void validate_InBlackList(String ip) throws Exception {
		if(null != AntiCheatingContent.getBlacklist().get(ip)){
			throw new BusinessException(Errors.IN_BLACKLIST);
		}
	}

	@Override
	public boolean validate_access(String ip) {
		ConcurrentHashMap<String, AtomicInteger> map = AntiCheatingContent.getAccessRecord();
		map.putIfAbsent(ip, new AtomicInteger(0));
		AtomicInteger num = map.get(ip);
		if(null != num){
			
			Integer accessNum = num.incrementAndGet();
			if(accessNum > access_limit){
				return false;
			}
		}
		return true;
	}

	@Override
	public void addBlackList(String ip) {
		
		Long time = new Long(DateTimeUtils.getCurrentMillis());
		AntiCheatingContent.getBlacklist().put(ip, time);
	}

}
