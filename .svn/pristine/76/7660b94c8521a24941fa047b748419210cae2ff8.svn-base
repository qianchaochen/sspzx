package com.gionee.ssp.sao.redis.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gionee.ssp.sao.redis.SspRedisSao;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
@Repository
public class SspRedisSaoImpl extends BaseSspRedisSaoImpl implements SspRedisSao {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void putObject(final String key, final String value) {
		try {
			int index = jedisConnectionFactoryManager.getIndexOfRedis();
			int maxReTryTimes = jedisConnectionFactoryManager.getMaxReTryTimes();
			this.doPut(0, maxReTryTimes, key, value, index);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}

	}

	@Override
	public String getObject(final String key) {
		try {
			int index = jedisConnectionFactoryManager.getIndexOfRedis();
			int maxReTryTimes = jedisConnectionFactoryManager.getMaxReTryTimes();
			return this.doGet(0, maxReTryTimes, index, key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
	}


	@Override
	public void putList(final String key, final String value) {
		try {
			int index = jedisConnectionFactoryManager.getIndexOfRedis();
			int maxReTryTimes = jedisConnectionFactoryManager.getMaxReTryTimes();
			this.doPutList(0, maxReTryTimes, key, value, index);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
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


}
