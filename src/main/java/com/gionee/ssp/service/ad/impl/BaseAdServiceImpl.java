package com.gionee.ssp.service.ad.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.service.ad.AdInfoService;
import com.gionee.ssp.service.ad.AdMd5RspService;
import com.gionee.ssp.service.ad.AdRspService;
import com.gionee.ssp.service.debug.DebugService;
import com.gionee.ssp.service.push.PushAdService;
import com.gionee.ssp.service.push.rsp.PushRspService;

/**
 * @author dingyw
 *
 * 2017年9月6日
 */
public class BaseAdServiceImpl {
    /**
     * 测试广告服务层
     */
    @Autowired
    DebugService debugService;
    
    
    /**
     *广告位信息服务层 
     */
    @Autowired
    AdInfoService adInfoService;
    
    
    /**
     * push返回信息服务层
     */
    @Autowired
    PushRspService pushRspService;
    
    
    /**
     * 竞价类广告返回信息服务层
     */
    @Autowired
    AdRspService adRspService;
    
    /**
     * 图片Md5返回广告层
     */
    @Autowired
    AdMd5RspService adMd5RspService;
    
    
    /**
     * 直投广告层
     */
    @Autowired
    PushAdService pushAdService;

}
