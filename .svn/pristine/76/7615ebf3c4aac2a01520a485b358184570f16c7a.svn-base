package com.gionee.ssp.service.wk;
/**
 * @description: 防作弊接口
 */
public interface AntiCheatingService {

	/**
	 * @title: validate_InBlackList
	 * @description: 校验是否在黑名单里
	 */
	public void validate_InBlackList(String ip) throws Exception;
	
	/**
	 * @title: validate_access
	 * @description: 校验请求是否为作弊请求
	 */
	public boolean validate_access(String ip); 
	
	/**
	 * @title: addBlackList
	 * @description: 加入黑名单
	 * @param ip
	 */
	public void addBlackList(String ip);
}
