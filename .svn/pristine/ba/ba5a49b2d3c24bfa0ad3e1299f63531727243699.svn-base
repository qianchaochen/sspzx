package com.gionee.ssp.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.sao.redis.SspRedisSao;
import com.gionee.ssp.service.redis.RedisService;

/**redis服务层
 * @author dingyw
 *
 * 2017年9月5日
 */
@Service
public class RedisServiceImpl implements RedisService{
	
	/**
	 * redis接口层
	 */
	@Autowired
	SspRedisSao redisSao;
	
	@Override
	public String query(String key) throws Exception {
		//查找对应的value
		return redisSao.getObject(key);
	}

	@Override
	public void save(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		redisSao.putObject(key, value);
	}
}
