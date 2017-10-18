package com.gionee.ssp.service.adFlow.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.adFlow.AdFlowModeSelectService;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年4月19日 
 */
@Service
public class AdFlowModeSelectServiceImpl extends BaseAdFlowModeSelectServiceImpl implements AdFlowModeSelectService{
	
	@Override
	public SdkResponseVO getAd(FillingDataVO fillingDataVO,SdkRequestVO requestVO) throws Exception{
		
		//某些规则只能是单个模式,比如软件商店或游戏大厅，不能广播
		if(true==adFlowModeFilterService.doFilterPackage(fillingDataVO, requestVO)){
			return this.getAdBySingleMode(fillingDataVO, requestVO);
		}
		
		if(AD_FLOW_MODE.equals("0")){
			//旧模式，事先确定发送给哪个ADX
			return this.getAdBySingleMode(fillingDataVO, requestVO);
		}else{
			//新模式，一次请求所有的ADX的，然后根据流量配置，确定最终选择哪个ADX的内容
			return this.getAdByMultiMode(fillingDataVO, requestVO);
		}
	}
	/**新模式，一次请求所有的ADX的，然后根据流量配置，确定最终选择哪个ADX的内容
	 * @param fillingDataVO
	 * @param requestVO
	 * @param wkSSPRequest
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getAdByMultiMode(FillingDataVO fillingDataVO,SdkRequestVO requestVO)throws Exception{
		
		//根据广告位流量配置来获取ADX列表。
		List<AdxInfoVO> adx_list=adxListService.getAdxList(fillingDataVO);
		
		//广播所有的ADX
		Map<String, SdkResponseVO> resultMap=broadCastService.broadCastBidRequest(fillingDataVO, requestVO, adx_list);
		
		//有返回内容的ADX，根据配置的级别和流量配比进行选择当前配置需要的广告
		return adFlowCtrlService.getAd(resultMap,fillingDataVO);
		
		
	}
	/**	//旧模式，事先确定发送给哪个ADX
	 * @param fillingDataVO
	 * @param requestVO
	 * @param wkSSPRequest
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getAdBySingleMode(FillingDataVO fillingDataVO,SdkRequestVO requestVO)throws Exception{
        
    	// 获取ADX信息,事先确定ADX的目标地址
        AdxInfoVO adxInfoVO = adxSelectService.getADXDestination(fillingDataVO);
        
        WKLogManager.getLOG().addReqAdLog(Constant.TARGET_ADX, adxInfoVO.getAdxCode());
		
        //获取第三方的广告
		if(!String.valueOf(AdxType.GIONEE.getAdx()).equals(adxInfoVO.getAdxCode()) && !String.valueOf(AdxType.WANKA.getAdx()).equals(adxInfoVO.getAdxCode())){
			//如果不是竞价广告
			return thirdAdService.getThirdAd(adxInfoVO, requestVO,fillingDataVO);
		}else{
			//获取竞价广告
			SdkResponseVO sdkResponseVO= bidAdService.getAd(adxInfoVO,requestVO,fillingDataVO);
			//记录返回返回广告的个数
			WKLogManager.getLOG().addReqAdLog("res_ad_cnt", String.valueOf(sdkResponseVO.getAdms().size()));
			
			return sdkResponseVO;
			
		}
	}
	
}
