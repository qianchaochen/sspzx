package com.gionee.common.redis.factory;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisShardInfo;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public class DmpJedisConnectionFactoryManager extends JedisConnectionFactoryManager{
	/**
	 * 连接密码
	 */
	private String password; 

	
	/**读取配置信息，初始化JedisConnectionFactory
	 * @param prop
	 * @throws Exception
	 */
	@PostConstruct
	@Override
	public void init() throws Exception {
		 for(int i=0;i<hosts_list.size();i++){
				String hostAndPort = (String)hosts_list.get(i);
				String arrays[]=hostAndPort.split(":");
				String host = arrays[0];
				int port = Integer.parseInt(arrays[1]);
				JedisShardInfo shardInfo = new JedisShardInfo(host, port);
				if(!StringUtils.isEmpty(password)){
					shardInfo.setPassword(password);
				}
				JedisConnectionFactory jedis = new JedisConnectionFactory(poolConfig);
				jedis.setUsePool(true);
				jedis.setShardInfo(shardInfo);
				jedis.afterPropertiesSet();
				jedisConnectionFactories.add(jedis);
			}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
