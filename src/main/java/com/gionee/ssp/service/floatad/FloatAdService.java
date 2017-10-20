package com.gionee.ssp.service.floatad;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author: qiancc
 * 2017年10月19日
 */
public interface FloatAdService {
    /**
     * 浮标广告域名限制（屏蔽或定投）处理
     * @param fillingDataVO
     * @param res
     * @param rsp
     * @throws Exception
     */
    public void domainFilter(FillingDataVO fillingDataVO, SdkRequestVO res, SdkResponseVO rsp) throws Exception;
}
