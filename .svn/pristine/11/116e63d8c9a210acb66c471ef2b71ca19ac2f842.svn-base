package com.gionee.ssp.service.cp.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.cp.CheckCpService;

/**
 * @author dingyw
 *
 * 2017年9月6日
 */
@Service
public class CheckCpServiceImpl extends BaseCpServiceImpl implements CheckCpService{

	
	@Override
	public boolean isLock(String appId, String adslotId){
		if(lock_appId.equals(appId) && (lock_adslotId.equals(adslotId) || lock_sdk_adslotId.equals(adslotId))){
				return true;
		}
		return false;
	}
	
	@Override
	public boolean isLockOld(String appId, String adslotId){
		if(lock_appId.equals(appId) && lock_adslotId.equals(adslotId)){
				return true;
		}
		return false;
	}
	@Override
	public boolean isMusic(String appId, String adslotId){
		if(music_appId.equals(appId) && music_adslotId.equals(adslotId)){
				return true;
		}
		return false;
	}

}
