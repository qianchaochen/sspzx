package com.gionee.ssp.service.push.req.convert.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.push.req.convert.PushConvertReqService;
import com.wk.model.adx.WKSSP.AdSlot;
import com.wk.model.adx.WKSSP.Device;
import com.wk.model.adx.WKSSP.WKSSPRequest;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.mvc.ipush.utils.AreaUtils;

/**直投广告请求转换
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class PushConvertReqServiceImpl extends BasePushConvertReqServiceImpl implements PushConvertReqService{
	
	@Override
    public QueryVO convertReq(WKSSPRequest wKSSPRequest) throws Exception {
        QueryVO queryVO = new QueryVO();

        AdSlot adSlot = wKSSPRequest.getAdSlot();

        //根据不同广告类型，转换不同创意
        int ad_type =  convertPushReqCreativeService.getCreativeInfo(wKSSPRequest, queryVO);
        
        queryVO.setAd_type(ad_type); // 创意类型
        queryVO.setAdslot(Integer.valueOf(adSlot.getId())); // 广告位id

        Device device = wKSSPRequest.getDevice();
        queryVO.setArea(AreaUtils.getAreaCodeByIP(device.getIpv4())); // 地域编码

        queryVO.setConnectionType(this.getConnectionType(device)); // 连接类型
        
        queryVO.setCarrier(device.getCarrier().getNumber()); // 运营商
        queryVO.setOs(device.getOsType().toLowerCase()); // 操作系统类型
        queryVO.setOsv(device.getOsVersion()); // 操作系统版本
        queryVO.setModel(this.getModelByMakeAndModel(device.getVendor(), device.getModel())); // 机型
        queryVO.setBitc(adSlot.getBitcList()); // 屏蔽动作类型
        queryVO.setCount(adSlot.getCount()); // 广告条数

        // 设置宏替换需要的字段
        queryVO.setIp(device.getIpv4()); // 设置ip
        queryVO.setImeiMd5(device.getImeiMd5()); // 设置imei
        queryVO.setBidRequestId(wKSSPRequest.getRequestId()); // 设置竞价请求的id
        queryVO.setDpidMd5(device.getAndroidIdMd5()); // 设置android的md5值

        return queryVO;
    }
}
