package com.gionee.ssp.service.debug.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adapter.BannerAdapter;
import com.gionee.ssp.service.adapter.InstlAdapter;
import com.gionee.ssp.service.adapter.SplashAdapter;
import com.gionee.ssp.service.debug.DebugImageService;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.DataUtils;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
@Service
public class DebugImageServiceImpl extends BaseDebugServiceImpl implements DebugImageService{
	
	/**
	 * 开屏adapter
	 */
	@Autowired
	SplashAdapter splashAdapter;
	
	/**
	 *插屏adapter 
	 */
	@Autowired
	InstlAdapter instlAdapter;
	
	/**
	 * 横幅adapter
	 */
	@Autowired
	BannerAdapter bannerAdapter;
	
	private static String BANNER_32050 = "banner_320*50"; //320*50横幅创意测试地址 
	private static String BANNER_640100 = "banner_640*100"; //640*100横幅创意测试地址 
	
	private static String INSERT_320250 = "intl_300*250"; //320*250插屏创意测试地址
	private static String INSERT_480320 = "intl_480*320"; //480*320插屏创意测试地址
	private static String INSERT_600500 = "intl_600*500"; //600*500插屏创意测试地址
	private static String INSERT_960640 = "intl_960*640"; //960*640插屏创意测试地址
	
	private static String SPLASH_480720 = "splash_480*720"; //480*720开屏创意测试地址
	private static String SPLASH_480800 = "splash_480*800"; //480*800开屏创意测试地址
	private static String SPLASH_480854 = "splash_480*854"; //480*854开屏创意测试地址
	private static String SPLASH_600960 = "splash_640*960"; //600*960开屏创意测试地址
	private static String SPLASH_6401136 = "splash_640*1136"; //640*1136开屏创意测试地址
	private static String SPLASH_7681280 = "splash_768*1280"; //768*1280开屏创意测试地址
	private static String SPLASH_10801920 = "splash_1080*1920"; //1080*1920开屏创意测试地址
	
	@Override
	public void setDebugImgUrl(SdkResponseAdVO vo, FillingDataVO fillingDataVO, SdkRequestVO req){
		
		String imgUrl = "";
		
		Map<String, Integer> adslotWH = null; //适配后的结果
		int adslotW = req.getDevice().getW();
		int adslotH = req.getDevice().getH();
		int w = 0; //广告位宽
		int h = 0; //广告位高
		
		//debug开屏广告
		if(CommonConstant.IS_TRUE.FALSE.getValue() != fillingDataVO.getIs_splash()){	
			//开屏
			adslotWH = DataUtils.DataAdapter(splashAdapter.getAdapter(), adslotW, adslotH);
			w = adslotWH.get(Constant.ADSLOT_WIDTH);
			h = adslotWH.get(Constant.ADSLOT_HIGH);
			
			if(480 == w && 720 ==h){
				imgUrl = getCreative(SPLASH_480720);
			} else if(480 == w && 800 == h){
				imgUrl = getCreative(SPLASH_480800);
			} else if(480 == w && 854 == h){
				imgUrl = getCreative(SPLASH_480854);
			} else if(640 == w && 960 == h){
				imgUrl = getCreative(SPLASH_600960);
			} else if(640 == w && 1136 == h){
				imgUrl = getCreative(SPLASH_6401136);
			} else if (768 == w && 1280 == h) {
			    imgUrl = getCreative(SPLASH_7681280);
			} else {
                imgUrl = getCreative(SPLASH_10801920);
			}
			
		} else if(CommonConstant.IS_TRUE.TRUE.getValue() == fillingDataVO.getInstl()){ 
			//插屏
			adslotWH = DataUtils.DataAdapter(instlAdapter.getAdapter(), adslotW, adslotH);
			switch(adslotWH.get(Constant.ADSLOT_WIDTH)){
			
			case 300 : {
				imgUrl = getCreative(INSERT_320250);
				break;
			}
			case 480 : {
				imgUrl = getCreative(INSERT_480320);
				break;
			}
			case 600 : {
				imgUrl = getCreative(INSERT_600500);
				break;
			}
			default : {
				imgUrl = getCreative(INSERT_960640);
				break;
			}
			}
		} else {
			//横幅
			adslotWH = DataUtils.DataAdapter(bannerAdapter.getAdapter(), adslotW, adslotH);
			if(320 == adslotWH.get(Constant.ADSLOT_WIDTH)){
				imgUrl = getCreative(BANNER_32050);
			} else {
				imgUrl = getCreative(BANNER_640100);
			}
		}
		
		setAD_W_H(vo, adslotWH.get(Constant.ADSLOT_WIDTH), adslotWH.get(Constant.ADSLOT_HIGH));
		vo.setImgurl(imgUrl);
		
	}

}
