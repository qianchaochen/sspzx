package com.gionee.ssp.sao.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.gionee.common.redis.factory.JedisConnectionFactoryManager;

/**
 * @author dingyw
 *
 * 2017年9月25日
 */
public class BaseSspRedisSaoImpl {
	
	/**
	 * jedis应用类
	 */
	@Autowired
	JedisConnectionFactoryManager jedisConnectionFactoryManager;
	
	protected String doGet(final int time, final int maxRetryTimes, final int index, String key) throws Exception {
		if (time == maxRetryTimes) {
			throw new Exception("redis database has down. \n");
		}
		try {
			RedisTemplate<String, String> redisTemplate = jedisConnectionFactoryManager.fetchRedisTemplate(index);
			return redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection connection) {
					byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
					if (connection.exists(redisKey)) {
						byte[] bytes = connection.get(redisKey);
						return redisTemplate.getStringSerializer().deserialize(bytes);
					}
					return null;
				}
			});
		} catch (Exception e) {
			// 若连接失败，重试
			Thread.sleep(10);
			return doGet(time + 1, maxRetryTimes, index + 1, key);
		}
	}
	protected void doPut(final int time, final int maxRetryTimes, String key, String value, final int index) throws Exception {
		if (time == maxRetryTimes) {
			throw new Exception("redis database has down. \n");
		}
		try {
			RedisTemplate<String, String> redisTemplate = jedisConnectionFactoryManager.fetchRedisTemplate(index);
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) {
					byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
					byte[] valueByte = redisTemplate.getStringSerializer().serialize(value);
					connection.set(redisKey, valueByte);
					return null;
				}
			});
		} catch (Exception e1) {
			// 若连接失败，重试
			Thread.sleep(10);
			doPut(time + 1, maxRetryTimes, key, value, index + 1);
		}
	}

	
	protected void doPutList(final int time, final int maxRetryTimes, String key, String value,
			final int index) throws Exception {
		if (time == maxRetryTimes) {
			throw new Exception("redis database has down. \n");
		}
		try {
			RedisTemplate<String, String> redisTemplate = jedisConnectionFactoryManager.fetchRedisTemplate(index);
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) {
					byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
					byte[] valueByte = redisTemplate.getStringSerializer().serialize(value);
					connection.sAdd(redisKey, valueByte);
					return null;
				}
			});
		} catch (Exception e1) {
			// 若连接失败，重试
			Thread.sleep(10);
			doPutList(time + 1, maxRetryTimes, key, value, index + 1);
		}
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
