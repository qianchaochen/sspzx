package com.gionee.ssp.sao.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.gionee.common.redis.factory.DmpJedisConnectionFactoryManager;
import com.gionee.ssp.sao.redis.DmpRedisSao;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
@Repository
public class DmpRedisSaoImpl implements DmpRedisSao {
	
	Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * jedis应用类
	 */
	@Autowired
	DmpJedisConnectionFactoryManager jedisConnectionFactoryManager;
	
	@Override
	public List<String> getList(String key) {
		try {
			int index = jedisConnectionFactoryManager.getIndexOfRedis();
			int maxReTryTimes = jedisConnectionFactoryManager.getMaxReTryTimes();
			return this.doGetList(0, maxReTryTimes, key, index);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
	}
	protected List<String> doGetList(final int time, final int maxRetryTimes, String key,
			final int index) throws Exception {
		
		if (time == maxRetryTimes) {
			throw new Exception("redis database has down. \n");
		}
		try {
			RedisTemplate<String, String> redisTemplate = jedisConnectionFactoryManager.fetchRedisTemplate(index);
			return redisTemplate.execute(new RedisCallback<List<String>>() {
				@Override
				public List<String> doInRedis(RedisConnection connection) {
					byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
					Set<byte[]> values = connection.sMembers(redisKey);
					
					List<String> list =new ArrayList<String>();
					for (byte[] item : values) {
						String value = redisTemplate.getStringSerializer().deserialize(item);
						list.add(value);
					}
					return list;
				}
			});
		} catch (Exception e1) {
			// 若连接失败，重试
			Thread.sleep(10);
			return doGetList(time + 1, maxRetryTimes, key, index + 1);
		}
	}
}
