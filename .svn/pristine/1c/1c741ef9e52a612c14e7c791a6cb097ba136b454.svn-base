package com.gionee.ssp.service.broadcast;

import java.util.List;
import java.util.Map;

import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;


/**
 * @author dingyw
 *
 * 2017年4月20日
 */
public interface BroadCastService {

    /**根据请求，异步广播分发到
     * @param fillingDataVO
     * @param requestVO
     * @param wkSSPRequest
     * @return
     * @throws Exception 
     */
    public Map<String, SdkResponseVO> broadCastBidRequest(FillingDataVO fillingDataVO,SdkRequestVO requestVO,List<AdxInfoVO> adx_list) throws Exception;
}
