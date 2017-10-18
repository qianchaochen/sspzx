package com.gionee.ssp.sao.redis;

import java.util.List;



/**redis接口层
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface DmpRedisSao {
	/**一个key，取出多个值
	 * @param key
	 * @return
	 */
	public List<String> getList(final String key);
	
}
