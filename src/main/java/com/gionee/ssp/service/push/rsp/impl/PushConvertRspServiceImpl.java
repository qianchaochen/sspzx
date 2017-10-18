package com.gionee.ssp.service.push.rsp.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.rsp.PushConvertRspService;
import com.google.protobuf.ByteString;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.model.adx.WKSSP.CreativeType;
import com.wk.model.adx.WKSSP.InteractionType;
import com.wk.model.adx.WKSSP.WKSSPResponse;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**直投广告请求转换
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class PushConvertRspServiceImpl extends BasePushConvertRspServiceImpl implements PushConvertRspService{
	
	@Override
    public WKSSPResponse convertRsp(final List<CampaignVO> list,
            final QueryVO queryVO, final SdkRequestVO req, final String requestId) throws Exception {

    	WKSSPResponse.Builder builder = WKSSPResponse.newBuilder();

        if (ObjectUtils.isEmpty(list)) {
            builder.setCode(CommonConstant.SDK_RSP_CODE.NO_CONTENT.getValue());
            builder.setMsg(ByteString.copyFrom("no content".getBytes(Constant.CHARSET)));
            return builder.build();
        } 
    
        builder.setCode(CommonConstant.SDK_RSP_CODE.NO_ERROR.getValue());

        int impId = 1;
        for (CampaignVO vo : list) {
        	if(!this.demo_package_name.equals(vo.getBundle())){
                WKLogManager.getLOG().addReqAdLog("campaign_id", vo.getCampaign_id() + "");
                WKLogManager.getLOG().addReqAdLog("creative_id", vo.getCreatives()[0].getCreative_id() + "");
        	}
            CreativeVO creativeVO = vo.getCreatives()[0];

            Ad.Builder adBuilder = Ad.newBuilder();
            adBuilder.setAdslotId(String.valueOf(queryVO.getAdslot())); // 广告位id
            adBuilder.setCreativeType(CreativeType.valueOf(0)); // 创意类型--暂时未使用--默认为0
            adBuilder.setBundle(vo.getBundle()); // 下载类广告
            adBuilder.setW(creativeVO.getW()); // 素材宽度
            adBuilder.setH(creativeVO.getH()); // 岁才高度
            
            //交互方式
            adBuilder.setInteractionType(InteractionType.valueOf(vo.getCampaign_target()));
            
            //设置监播
            pushTrackerService.getTrackerInfo(vo, adBuilder, req, queryVO, requestId, impId);
            
            //广告adm
            pushConvertAdmService.getAdm(creativeVO, vo, adBuilder);
            
            builder.addAds(adBuilder.build());

            impId++;
        }
        builder.setAd(builder.getAds(0));

        return builder.build();
    }
}
