package com.gionee.ssp.service.push.rsp;

import java.util.List;

import com.wk.model.adx.WKSSP.WKSSPResponse;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**push转换rsp服务层
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface PushConvertRspService {
	
    /**push转换rsp
     * @param campaignVOs
     * @param queryVO
     * @param requestVO
     * @param requestId
     * @return
     * @throws Exception
     */
    public WKSSPResponse convertRsp(final List<CampaignVO> campaignVOs,
            final QueryVO queryVO, final SdkRequestVO requestVO, final String requestId) throws Exception;

}
