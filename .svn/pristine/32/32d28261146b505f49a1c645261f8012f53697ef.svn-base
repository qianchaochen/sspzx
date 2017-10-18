package com.gionee.ssp.thread.anti;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import com.gionee.ssp.service.antiCheating.AntiCheatingContent;

/**防刷策略，每秒刷新一次用户访问记录表
 * @author dingyw
 *
 * 2017年9月6日
 */
public class IpMapAccessResetThread extends TimerTask {

	@Override
	public void run() {
		Map<String, AtomicInteger> accessRecord = AntiCheatingContent.getAccessRecord();
		accessRecord.clear();
	}

}
