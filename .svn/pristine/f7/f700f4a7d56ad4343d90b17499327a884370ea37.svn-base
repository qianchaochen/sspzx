package com.gionee.ssp.service.push.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.push.PushAdService;
import com.wk.model.adx.WKSSP.WKSSPRequest;
import com.wk.model.adx.WKSSP.WKSSPResponse;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**获取直投广告
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class PushServiceImpl extends BasePushServiceImpl implements PushAdService {

	@Override
	public SdkResponseVO getPushAd(SdkRequestVO req, String requestId, FillingDataVO vo)
			throws Exception {

		// 将sdk请求转为标准的probuf请求
		WKSSPRequest wkSSPRequest = convertReqService.getReq(req, vo);

		//核心流程
		WKSSPResponse wkSSPResponse = this.getPushAd(wkSSPRequest, req, requestId);

		//将probuf响应转为sdk响应
		return convertRspService.getSdkRsp(wkSSPResponse, vo);
	}
	
	/**设置查询条件，到ES查询广告
	 * @param wKSSPRequest
	 * @param req
	 * @param req_id
	 * @return
	 * @throws Exception
	 */
	private WKSSPResponse getPushAd(WKSSPRequest wKSSPRequest, SdkRequestVO req, String req_id)
			throws Exception {

		//将probuf请求转为query请求
		QueryVO vo = pushConvertReqService.convertReq(wKSSPRequest);

		//设置dmp查询条件
		pushDmpService.setDmpQueryInfo(vo); 
	
		// ES搜索符合条件的结果
		List<CampaignVO> list = adSearchService.search(vo);

		// 结果排序
		List<CampaignVO> result_list = pushResultService.getList(list, vo);

		//将es的结果转为probuf
		WKSSPResponse response = pushConvertRspService.convertRsp(result_list, vo,req, req_id);
		
		return response;
	}
}
