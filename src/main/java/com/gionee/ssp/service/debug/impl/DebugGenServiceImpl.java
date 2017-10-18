package com.gionee.ssp.service.debug.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.debug.DebugGenService;
import com.wk.ssp.vo.FillNativeVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkMixVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**生成服务层
 * @author dingyw
 *
 * 2017年9月11日
 */
@Service
public class DebugGenServiceImpl extends BaseDebugServiceImpl implements DebugGenService{
	
	private static String TEXTICON_ICON = "texticon_icon"; //icon创意
	
	private static String NATIVE_SMALL_120120 = "native_small_120*120"; //120*120原生小图创意测试
	private static String NATIVE_SMALL_480360 = "native_small_480*360"; //480*360原生小图创意测试
	private static String NATIVE_BIG_600400 = "native_big_600*400"; //600*400原生大图创意测试
	private static String NATIVE_BIG_1000500 = "native_big_1000*500"; //1000*500原生大图测试
	private static String NATIVE_BIG_19201080 = "native_big_1920*1080"; //1920*1080原生大图测试
	private static String NATIVE_GRUOP = "native_group"; //原生组图测试
	
	
	@Override
	public void genDownAd(SdkResponseAdVO adVO){
		adVO.setClkurl(debug_download_url);
		adVO.setBundle(debug_bundle);
		adVO.setInteraction_type(CommonConstant.SDKInteractionType.DOWNLOAD.getValue());
		adVO.setDwnltrackers(new ArrayList<>());
		adVO.setActvtrackers(new ArrayList<>());
		adVO.setIntltrackers(new ArrayList<>());
	}
	
	@Override
	public void genMix(SdkResponseAdVO vo, FillingDataVO data){	
		SdkMixVO mixVO = new SdkMixVO();
		mixVO.setTitle("时下热门手机游戏礼包、激活码来袭");
		mixVO.setSub_title("时下最热门的手机游戏福利来了，钻石、金币、道具都不在话下，赶快来领取吧");
		mixVO.setImgurl(getCreative(TEXTICON_ICON));
		mixVO.setAcimgurl("");
		
		this.setAD_W_H(vo, 120, 120);
		vo.setCreative_type(CommonConstant.CreativeType.MIX.getValue());
		vo.setMix(mixVO);	
	}
	@Override
	public void genNative(SdkResponseAdVO vo, FillingDataVO fillingDataVO){

		SdkNativeVO sdkNativeVO = new SdkNativeVO();
		List<FillNativeVO> list = fillingDataVO.getNativ();
		
		//随机大图、小图、组图
		int index = RandomUtils.nextInt(0, list.size());
		
		for(int i =0 ;i<list.size();i++){
			FillNativeVO nativeVO = list.get(i);
			
			if(i == index){		
				String title = ""; //主标题
				String subTitle = ""; //子标题
				int w = nativeVO.getW();
				int h = nativeVO.getH();
				List<String> img = new ArrayList<>(); //创意列表
				int native_material_type = nativeVO.getType();
				int maxTitleLengh = nativeVO.getMax_m_title();
				int maxSubTitleLengh = nativeVO.getMax_sub_title();

				if(CommonConstant.NATIVE_MATERIAL_TYPE.GROUP_IMG.getValue() == native_material_type){
					title = "海量精选手机游戏礼包、激活码来袭，约吗？";
					img = this.getCreatives(NATIVE_GRUOP);
				}else if(CommonConstant.NATIVE_MATERIAL_TYPE.SMALL_IMG.getValue() == native_material_type){
					title = "发现一个您玩的手机游戏礼包，是否前去领取？";
					subTitle = "您有一款未领取的独家特权礼包，快来领取吧！";
					if(w == 120 && h== 120){
						img.add(getCreative(NATIVE_SMALL_120120));
					} else if(w == 480 && h == 360){
						img.add(getCreative(NATIVE_SMALL_480360));
					}
				}else if(CommonConstant.NATIVE_MATERIAL_TYPE.BIG_IMG.getValue() == native_material_type){
					title = "288888金币特权礼包来了，是否前去抢？";
					subTitle = "金币特权礼包您还未领取，是否去领取？";
					if(w == 1000 && h== 500){
						img.add(getCreative(NATIVE_BIG_1000500));
					} else if(w == 600 && h == 400){
						img.add(getCreative(NATIVE_BIG_600400));
					} else if(w == 1920 && h == 1080){
                        img.add(getCreative(NATIVE_BIG_19201080));
					}
				}
				
				//原生创意宽高
				setAD_W_H(vo, w, h);
				sdkNativeVO.setImgurl(img); //填充创意列表
				
				//添加标题
				if(title.length() > maxTitleLengh){
					title = title.substring(0, maxTitleLengh);
				}
				if(subTitle.length() > maxSubTitleLengh){
					subTitle = subTitle.substring(0, maxSubTitleLengh);
				}
				sdkNativeVO.setTitle(title);
				sdkNativeVO.setSub_title(subTitle);
				sdkNativeVO.setType(nativeVO.getType());
				sdkNativeVO.setSource("");
				
			}
		}
		//重新更改创意类型
		vo.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
		vo.setNativ(sdkNativeVO);
	}

}
