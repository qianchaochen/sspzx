package com.gionee.ssp.service.redis.dmp;

import java.util.List;


/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface DmpRedisService {
    
    /**
     * @description: 获取用户标签列表
     * @param imeiMd5
     * @return List<String>  用户标签
     * @throws Exception
     */
    public List<String> queryList(String imeiMd5) throws Exception;
}
