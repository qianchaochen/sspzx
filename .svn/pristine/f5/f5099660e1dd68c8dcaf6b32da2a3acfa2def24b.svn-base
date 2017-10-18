package com.gionee.ssp.sao.redis;

import java.util.List;



/**redis接口层
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface SspRedisSao{
	
	/**放入redis缓存
	 * @param key
	 * @param value
	 */
	public void putObject(final String key, final String value);
	
	/**获取数据
	 * @param key
	 * @return
	 */
	public String getObject(final String key);
	

	/**一个key，取出多个值
	 * @param key
	 * @return
	 */
	public List<String> getList(final String key);
	
	
	/**放入缓存,一个key对应多个value
	 * @param key
	 * @param value
	 */
	public void putList(final String key, final String value);
	
	
}
