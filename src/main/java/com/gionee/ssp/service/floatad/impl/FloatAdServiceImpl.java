package com.gionee.ssp.service.floatad.impl;

import com.gionee.ssp.service.ad.impl.BaseAdServiceImpl;
import com.gionee.ssp.service.conf.ad.AdConfService;
import com.gionee.ssp.service.floatad.FloatAdService;
import com.gionee.ssp.vo.FloatAdConfigVo;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.FillFloatVo;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 浮标广告服务层
 * @author: qiancc
 * 2017年10月20日
 */
public class FloatAdServiceImpl extends BaseAdServiceImpl implements FloatAdService {

    /**
     * 广告位配置服务层
     */
    @Autowired
    private AdConfService adConfService;

    @Override
    public void domainFilter(FillingDataVO fillingDataVO, SdkRequestVO res, SdkResponseVO rsp) throws Exception {
        List<SdkResponseAdVO> adms = rsp.getAdms();

        //从redis中获取浮标广告位的相关配置信息
        FloatAdConfigVo cfgVo = adConfService.getFloatAdCfg(Constant.REDIS_KEY + Constant.SSP_FLOAT_AD_CFG);
        if (cfgVo == null) {
            cfgVo = new FloatAdConfigVo();
        }
        FillFloatVo floatVo = fillingDataVO.getFloatVo();

        if (floatVo != null) {//如果是浮标类型的广告

            //移除浮标广告配置黑名单列表中的相关广告
            List<String> blackList = cfgVo.getBlackList();
            removeListAd(adms, blackList);

            //广告位中指定的浮标广告域名限制
            int type = floatVo.getDomainRestrict();
            List<String> list = floatVo.getDomainList();

            if (type == 0) {//屏蔽list列表域名下的广告
                removeListAd(adms, list);
            } else if (type == 1) {//只投放list列表域名下的广告，此时list列表不允许为空（前端页面控制）
                addListAd(rsp, adms, list);
            }
        }

        rsp.setDelayShow(cfgVo.getDelayShow());
        rsp.setSetTimeOut(cfgVo.getSetTimeOut());
    }

    /**
     * 将域名列表下的广告移除
     * @param adms 广告列表
     * @param list 屏蔽的域名列表
     */
    private void removeListAd(List<SdkResponseAdVO> adms, List<String> list) {
        if (adms != null && list != null) {
            for (SdkResponseAdVO vo : adms) {
                for (String domain : list) {
                    if (vo.getH5_url().contains(domain)) {
                        adms.remove(vo);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 将广告列表adms中属于list列表域名的广告保留
     * @param rsp 响应对象
     * @param adms 广告列表
     * @param list 投放的域名列表
     */
    private void addListAd(SdkResponseVO rsp, List<SdkResponseAdVO> adms, List<String> list) {
        if (adms != null) {
            List<SdkResponseAdVO> addlist = new ArrayList<>();
            for (SdkResponseAdVO vo : adms) {
                for (String domain : list) {
                    if (vo.getH5_url().contains(domain)) {
                        addlist.add(vo);
                        break;
                    }
                }
            }
            rsp.setAdms(addlist);
        }
    }
}
