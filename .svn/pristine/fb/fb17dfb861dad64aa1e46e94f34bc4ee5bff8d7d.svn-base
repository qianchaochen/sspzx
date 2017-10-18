package com.gionee.ssp.service.push.req.convert.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.push.req.convert.ConvertPushNativService;
import com.wk.model.adx.WKSSP.AdSlot;
import com.wk.model.adx.WKSSP.Native;
import com.wk.model.adx.nativevo.request.NativeRequestVO;
import com.wk.model.adx.nativevo.request.ReqAssetsVO;
import com.wk.model.adx.nativevo.request.ReqImgVO;
import com.wk.model.adx.nativevo.request.ReqTitleVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeMessageVO;
import com.wk.ssp.utils.JsonUtils;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class ConvertPushNativServiceImpl implements ConvertPushNativService{
	
	@Override
	public void getNativInfo(AdSlot adSlot,List<CreativeMessageVO> result) throws Exception{
		Native nativ = adSlot.getNative();
        if (!StringUtils.isEmpty(nativ.getRequest())) {
            NativeRequestVO nativeRequestVO = JsonUtils.readJson2Object(nativ.getRequest(), NativeRequestVO.class);
            List<ReqAssetsVO> reqAssetsVOList = nativeRequestVO.getReqAssets();

            if (reqAssetsVOList.size() > 0) {
                for (ReqAssetsVO reqAssetsVO : reqAssetsVOList) {
                    CreativeMessageVO creativeMessageVO = new CreativeMessageVO();

                    creativeMessageVO.setItem_types(Arrays.asList(reqAssetsVO.getType())); // 原生广告的类型
                    ReqTitleVO reqTitleVO = reqAssetsVO.getReqTitle();
                    ReqImgVO reqimgVO = reqAssetsVO.getReqImg();

                    if (!ObjectUtils.isEmpty(reqTitleVO)) {
                        creativeMessageVO.setLen(reqTitleVO.getLen()); // 主标题最大长度
                        creativeMessageVO.setSub_len(reqTitleVO.getSub_len()); // 副标题最大长度

                    }

                    if (!ObjectUtils.isEmpty(reqimgVO)) {
                        creativeMessageVO.setW(reqimgVO.getW()); // 创意宽
                        creativeMessageVO.setH(reqimgVO.getH()); // 创意高
                    }

                    result.add(creativeMessageVO);
                }
            }
        }
	}

}
