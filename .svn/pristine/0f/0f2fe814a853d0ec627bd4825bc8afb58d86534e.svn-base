package com.gionee.ssp.service.third.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.baidu.BaiduAdService;
import com.gionee.ssp.service.inmobi.InmobiAdService;
import com.gionee.ssp.service.third.ThirdAdService;
import com.gionee.ssp.service.toutiao.ToutiaoAdService;
import com.gionee.ssp.service.zaker.ZakerAdService;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 第三方广告
 * @author dingyw
 *
 * 2017年4月19日
 */
@Service
public class ThirdAdServiceImpl implements ThirdAdService{
	
    /**
     *百度广告 
     */
    @Autowired
    BaiduAdService baiduHttpService;
	/**
	 * 今日头条广告
	 */
	@Autowired
    ToutiaoAdService toutiaoHttpService;
    /**
     * inmobi广告
     */
    @Autowired
    InmobiAdService inmobiHttpService;
    /**
     * zaker广告
     */
    @Autowired
    ZakerAdService zakerHttpService;
    
	
	public SdkResponseVO getThirdAd( AdxInfoVO adx,SdkRequestVO req,FillingDataVO vo) throws Exception{
		
		 if(CommonConstant.SYS_CODE.BAIDU_NAME.getValue().equals(adx.getAdxName())){ //如果分配到百度
    		 //百度
    		 return baiduHttpService.getAd(req, vo);
         }else if(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue().equals(adx.getAdxName())){ //如果分配到头条
    		 //今日头条
        	 return toutiaoHttpService.getAd(req, vo);
         }else if(CommonConstant.SYS_CODE.INMOBI_NAME.getValue().equals(adx.getAdxName())){ //如果分配到inmobi
    		 //inmobi
        	 return inmobiHttpService.getAd(req, vo);
         }else if(CommonConstant.SYS_CODE.ZAKER_NAME.getValue().equals(adx.getAdxName())){ //如果分配到zaker
    		 //zaker
        	 return zakerHttpService.getAd(req, vo);
         }else{
        	 return null;
         }
	}

}
