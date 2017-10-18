package com.gionee.common.redis.factory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public class JedisConnectionFactoryManager {

	/**
	 * 连接池配置
	 */
	protected JedisPoolConfig poolConfig; 
	
	/**
	 *最大重试次数 
	 */
	private Integer maxReTryTimes;
	
	/**
	 * jedis主机 ip:port 列表
	 */
	protected List<String> hosts_list;

	// ssp_redis的连接工厂列表
	protected List<JedisConnectionFactory> jedisConnectionFactories = new ArrayList<JedisConnectionFactory>();
	

	
	/**读取配置信息，初始化JedisConnectionFactory
	 * @param prop
	 * @throws Exception
	 */
	 @PostConstruct
	public void init() throws Exception {
		for(int i=0;i<hosts_list.size();i++){
			String hostAndPort = (String)hosts_list.get(i);
			String arrays[]=hostAndPort.split(":");
			String host = arrays[0];
			int port = Integer.parseInt(arrays[1]);
			JedisShardInfo shardInfo = new JedisShardInfo(host, port);

			JedisConnectionFactory jedis = new JedisConnectionFactory(poolConfig);
			jedis.setUsePool(true);
			jedis.setShardInfo(shardInfo);
			jedis.afterPropertiesSet();
			jedisConnectionFactories.add(jedis);
		}
	
	}

	/**获取index指定的factory
	 * @param index
	 * @return
	 */
	private JedisConnectionFactory fetchAvailableFactory(int index) {
		//index不能超出jedisConnectionFactories list的长度
		int indexOfRedis = index % jedisConnectionFactories.size();
		
		return jedisConnectionFactories.get(indexOfRedis);
	}

	/**获取随机的index
	 * @return
	 */
	public int getIndexOfRedis() {

		return RandomUtils.nextInt(0, this.jedisConnectionFactories.size());
	}

	/**获取index中指定的factory
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public RedisTemplate<String, String> fetchRedisTemplate(int index)
			throws Exception {
		JedisConnectionFactory fetchAvailableFactory = this.fetchAvailableFactory(index);
		if (fetchAvailableFactory == null) {
			throw new Exception("####no available factory ...");
		}
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(fetchAvailableFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}


	/**
	 * 销毁连接池
	 */
	@PreDestroy
	public void poolDestory() {
		for (int i=0;i<jedisConnectionFactories.size();i++) {
			jedisConnectionFactories.get(i).destroy();
		}
	}

	public List<String> getHosts_list() {
		return hosts_list;
	}

	public void setHosts_list(List<String> hosts_list) {
		this.hosts_list = hosts_list;
	}

	public Integer getMaxReTryTimes() {
		return maxReTryTimes;
	}

	public void setMaxReTryTimes(Integer maxReTryTimes) {
		this.maxReTryTimes = maxReTryTimes;
	}

	public List<JedisConnectionFactory> getJedisConnectionFactories() {
		return jedisConnectionFactories;
	}

	public void setJedisConnectionFactories(
			List<JedisConnectionFactory> jedisConnectionFactories) {
		this.jedisConnectionFactories = jedisConnectionFactories;
	}

	
}
