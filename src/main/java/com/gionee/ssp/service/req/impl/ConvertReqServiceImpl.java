package com.gionee.ssp.service.req.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.req.ConvertReqService;
import com.wk.model.adx.WKSSP.WKSSPRequest;
import com.wk.ssp.utils.local.ThreadLocalManager;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SDKInfoVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqServiceImpl extends BaseConvertReqServiceImpl implements ConvertReqService{
	
	@Override
	public WKSSPRequest getReq(SdkRequestVO sdkReq, FillingDataVO vo) throws Exception {

		WKSSPRequest bdRes = null; // ADX请求字段
		WKSSPRequest.Builder reqBuilder = WKSSPRequest.newBuilder(); // ADX请求生成器
		if (sdkReq != null) {
			reqBuilder.setRequestId(ThreadLocalManager.getLocal().getRequest_id());
			 // 填充应用信息
			convertReqAppService.setApp(reqBuilder, sdkReq, vo);
			// 填充设备信息
			convertReqDeviceService.setDevice(reqBuilder, sdkReq); 
			// 填充广告位信息
			convertReqAdSlotService.setAdSlot(reqBuilder, sdkReq, vo);
			//填充用户信息
			convertReqUserService.setUser(reqBuilder, vo.getUser());
			
			reqBuilder.setIsDebug(0); // 填充是否调试字段,非调试

			SDKInfoVO sdkInfoVO = sdkReq.getSdkInfoVO();
			if (!ObjectUtils.isEmpty(sdkInfoVO)) {
				// 填充SDK数据信息
				convertReqSdkService.setSDKInfo(reqBuilder, sdkInfoVO);
			}

			// 转换禁用广告类别
			convertReqBanAdCatalogService.setBanAdCatalog(reqBuilder, vo);
			
			// 转换禁用广告主
			convertReqBanAdvertiserService.setBanAdvertiser(reqBuilder, vo);
			
			bdRes = reqBuilder.build(); // 生成ADX请求字段
		}
		return bdRes;
	}

}
