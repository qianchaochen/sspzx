package com.gionee.ssp.service.push.rsp.tracker.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.push.rsp.tracker.PushTrackerService;
import com.gionee.ssp.service.wk.impl.BaseTrackerServiceImpl;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.utils.GeneratorUtils;
import com.wk.ssp.utils.MacroSubstituionUtils;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class PushTrackerServiceImpl extends BaseTrackerServiceImpl implements PushTrackerService{
	/**
	 *demo的包名 
	 */
	private String demo_package_name="com.gionee.ad.demo";
	
	@Override
	public void getTrackerInfo(CampaignVO vo, Ad.Builder adBuilder,SdkRequestVO req,QueryVO queryVO,String requestId,int impId){
        int campaign_id = vo.getCampaign_id();
		// 展示监播
        if (!StringUtils.isBlank(vo.getImptracker())) {
            adBuilder.addImptrackers( MacroSubstituionUtils.macroSubstitutionMMA(vo.getImptracker(), queryVO));
        }

        if(!this.demo_package_name.equals(vo.getBundle())){
            String impUrl = GeneratorUtils.generateDetecting(this.dealTrackerUrl(this.imp_tracker_url), req,
                    requestId, "&campaign_id=" + campaign_id + "&ipush=1&imp_id=" + impId+"&mode="+vo.getMode());
            adBuilder.addImptrackers(impUrl);
        }
           
        // 点击监播
        if (!StringUtils.isBlank(vo.getClktracker())) {
            adBuilder.addClktrackers(MacroSubstituionUtils.macroSubstitutionMMA(vo.getClktracker(), queryVO));
        }
        if(!this.demo_package_name.equals(vo.getBundle())){
            String clickUrl = GeneratorUtils.generateDetecting(this.dealTrackerUrl(this.click_tracker_url), req,
                    requestId, "&campaign_id=" + campaign_id + "&ipush=1&imp_id=" + impId+"&mode="+vo.getMode());
            adBuilder.addClktrackers(clickUrl); // 点击追踪地址
        }
       
	}

}
