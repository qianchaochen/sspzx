package com.gionee.ssp.service.push.rsp.convert.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.push.rsp.convert.PushConvertAdmService;
import com.wk.model.adx.AdmVO;
import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeVO;
import com.wk.ssp.utils.JsonUtils;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class PushConvertAdmServiceImpl implements PushConvertAdmService{
	
	@Override
	public void getAdm(CreativeVO creativeVO,CampaignVO vo,Ad.Builder adBuilder) throws Exception{
	
	    // 广告模板---start
	    AdmVO adm = new AdmVO();
	    if (StringUtils.isEmpty(creativeVO.getIcon())) {
	        adm.setIcon(Arrays.asList(StringUtils.split(creativeVO.getImg_url(), ",")).get(0));
	    } else {
	        adm.setIcon(creativeVO.getIcon());
	    }
	    adm.setIconaction(creativeVO.getIconaction());
	    if (!StringUtils.isEmpty(creativeVO.getImg_url())) {
	    	List<String> imgUrls = Arrays.asList(StringUtils.split(creativeVO.getImg_url(), ","));
	    	adm.setImgurl(imgUrls);
	    } else {
	        adm.setImgurl(new ArrayList<>());
	        adm.setImgmd5(new ArrayList<>());
	    }
	    adm.setLandingurl(vo.getLanding_url());
	    adm.setSource(creativeVO.getSource());
	    adm.setTitle(creativeVO.getTitle());
	    adm.setSubtitle(creativeVO.getSubtitle());
	    adm.setItemType(vo.getItem_type());
	
	    adBuilder.setAdm(JsonUtils.writeObject2Json(adm));
	    // 广告模板---end
	}

}
