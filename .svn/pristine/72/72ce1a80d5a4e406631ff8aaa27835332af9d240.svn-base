package com.gionee.ssp.thread.anti;

import java.util.Map;
import java.util.TimerTask;

import com.gionee.ssp.service.antiCheating.AntiCheatingContent;
import com.wk.ssp.utils.DateTimeUtils;


/**更新黑名单，将超过禁止访问的IP移除黑名单
 * @author dingyw
 *
 * 2017年9月6日
 */
public class UpdateBlackListThread extends TimerTask {

	@Override
	public void run() {
		Map<String, Long> blackList = AntiCheatingContent.getBlacklist();
		long currentTime = DateTimeUtils.getCurrentMillis();
		for(String key : blackList.keySet()){		
			if((int) ((currentTime - blackList.get(key))/(1000*3600)) > AntiCheatingContent.FORBID_TIME){
				blackList.remove(key);
			}
			
		}
	}

}
