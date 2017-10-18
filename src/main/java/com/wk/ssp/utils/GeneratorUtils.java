package com.wk.ssp.utils;

import java.util.UUID;

import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @description: 数据生成类
 */
public class GeneratorUtils {

    /**
     * @title: generateDetecting
     * @description: 监播地址生成工具
     * @param url
     * @param requestVO
     * @return
     */
    public static String generateDetecting(String url, SdkRequestVO requestVO, String requestId,
            String... ipushParameters) {

        String appId = requestVO.getApp().getApp_id();
        String adslotId = requestVO.getAdslot().getAdslot_id();
        String trackerUrl = url + "?request_id=" + requestId + "&app_id=" + appId + "&adslot_id=" + adslotId;
        StringBuilder reString = new StringBuilder();
		for(int i = 0; i < ipushParameters.length ; i++){
			reString.append(ipushParameters[i]);
		}
        
        trackerUrl += reString.toString();
        
        return trackerUrl;
    }

    /**
     * @title: generateRequestId
     * @description: requestId生成工具
     * @param requestVO
     * @return
     * @throws Exception
     */
    public static String generateRequestId(SdkRequestVO requestVO) throws Exception {

        String appId = requestVO.getApp().getApp_id();
        String adslotId = requestVO.getAdslot().getAdslot_id();
        long timesStamp = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        String requestId = appId + adslotId + timesStamp + uuid;

        return MD5Utils.md5AsString(requestId);
    }
}
