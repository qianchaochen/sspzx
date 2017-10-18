package com.gionee.ssp.service.cp;

/**
 * @author dingyw
 *
 * 2017年9月6日
 */
public interface CheckCpService {
	/**判断是否锁屏新的
	 * @param appId
	 * @param adslotId
	 * @return
	 */
	public boolean isLock(String appId, String adslotId);
	
	/**判断是否锁屏旧的
	 * @param appId
	 * @param adslotId
	 * @return
	 */
	public boolean isLockOld(String appId, String adslotId);
	
	/**判断是否音乐
	 * @param appId
	 * @param adslotId
	 * @return
	 */
	public boolean isMusic(String appId, String adslotId);

}
