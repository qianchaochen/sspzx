package com.gionee.ssp.service.redis.dmp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.sao.redis.DmpRedisSao;
import com.gionee.ssp.service.redis.dmp.DmpRedisService;
import com.wk.ssp.mvc.Constant;


/** DMP redis 操作实现类
 * @author dingyw
 *
 * 2017年9月5日
 */
@Service
public class DmpRedisServiceImpl implements DmpRedisService {

	/**
	 * redis接口层
	 */
	@Autowired
	DmpRedisSao dmpRedisSao;


	@Override
	public List<String> queryList(String imeiMd5) throws Exception {
		String redisKey=Constant.DMP_KEY.replace("${imeimd5}", imeiMd5);
		return dmpRedisSao.getList(redisKey);
	}
}
