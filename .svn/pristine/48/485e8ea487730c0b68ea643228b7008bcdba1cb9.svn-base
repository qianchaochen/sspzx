package com.wk.ssp.utils;

import java.util.ArrayList;
import java.util.List;

import com.wk.ssp.vo.API.APIAdmsVO;
import com.wk.ssp.vo.API.APIResponseVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @description: 
 */
public class APISDKConverUtils {

    public static APIResponseVO converResponse(SdkResponseVO responseVO) {
        APIResponseVO apiResponseVO = new APIResponseVO();
        apiResponseVO.setRequest_id(responseVO.getRequest_id());
        apiResponseVO.setError_code(responseVO.getError_code());
        apiResponseVO.setGet_ad_in_same_view_interval(responseVO.getGet_ad_in_same_view_interval());
        apiResponseVO.setGet_ad_in_same_view_times(responseVO.getGet_ad_in_same_view_times());//重复请求广告次数
        apiResponseVO.setExpiration_time(responseVO.getExpiration_time());
        List<APIAdmsVO> admsVOs = new ArrayList<>();
        for (SdkResponseAdVO admVO : responseVO.getAdms()) {
            APIAdmsVO admsVO = admConverAPI(admVO);
            admsVOs.add(admsVO);
        }
        apiResponseVO.setAdms(admsVOs);
        
        return apiResponseVO;
    }
    public static APIAdmsVO admConverAPI(SdkResponseAdVO admVO) {
        
        APIAdmsVO apiAdmsVO = new APIAdmsVO();
        apiAdmsVO.setAdslot_id(admVO.getAdslot_id());
        apiAdmsVO.setAd_type(admVO.getAd_type());
        apiAdmsVO.setCreative_type(admVO.getCreative_type());
        apiAdmsVO.setInteraction_type(admVO.getInteraction_type());
        apiAdmsVO.setBundle(admVO.getBundle());
        apiAdmsVO.setNativ(admVO.getNativ());
        apiAdmsVO.setMix(admVO.getMix());
        apiAdmsVO.setImgurl(admVO.getImgurl());
        apiAdmsVO.setW(admVO.getW());
        apiAdmsVO.setH(admVO.getH());
        apiAdmsVO.setClkurl(admVO.getClkurl());
        apiAdmsVO.setImptrackers(admVO.getImptrackers());
        apiAdmsVO.setClktrackers(admVO.getClktrackers());
        apiAdmsVO.setDwnlst(admVO.getDwnlst());
        apiAdmsVO.setDwnltrackers(admVO.getDwnltrackers());
        apiAdmsVO.setIntltrackers(admVO.getIntltrackers());
        apiAdmsVO.setActvtrackers(admVO.getActvtrackers());
        return apiAdmsVO;
    }
}
