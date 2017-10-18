package com.gionee.ssp.utils.baidu;

import java.util.regex.Pattern;

import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.gionee.ssp.exception.baidu.BaiduExpType;
import com.gionee.ssp.model.BaiduMobads.MaterialMeta.CreativeType;
import com.wk.ssp.vo.FillingDataVO;

public class BaiduApiUtil {
	
	/**
	 * 百度请求ID构建
	 * @param appId
	 * 			百度应用ID
	 * @param adslotId
	 * 			百度广告位ID
	 * @return appId_adslotId_UUID
	 */
	public final static String requestIdBuilder(String appId, String adslotId){ 
		
		/*return appId+adslotId+SeqIdSerial.genSeqId();*/
		return SeqIdSerial.genSeqId("gli");
	}
	
	/**
	 * 网络连接类型转换
	 * @param sdkConType
	 * 			SDK请求中网络连接类型
	 * @return	百度网络连接类型
	 */
	public final static int connectTypeFormat(int sdkConType){
		
		int baiduConType = 0;	
		switch (sdkConType) {
		case 0: baiduConType = 0; 	break;	//未知连接
		case 1:	baiduConType = 101; break;	//以太网
		case 2:	baiduConType = 100; break;	//WiFi
		case 3:	baiduConType = 1;	break;	//未知蜂窝网络
		case 4:	baiduConType = 2;	break;	//2G
		case 5:	baiduConType = 3;	break;	//3G
		case 6:	baiduConType = 4;	break;	//4G
		default:
			break;
		}
		return baiduConType;
	}
	
	/**
	 * 版本格式检查
	 * @param version
	 * @return
	 */
	public final static String versionCheck(String version){
		
		String defaultVer = "1.0.0"; //默认版本号
		
		if (version == null) return defaultVer;
		
		if(Pattern.matches("^[\\d]+\\.[\\d]+\\.[\\d]+$", version)){
			return version;
		}else if(Pattern.matches("^[\\d]+\\.[\\d]+$", version)){
			return version+".0";
		}else{
			return defaultVer;
		}
	}
	
	public final static int creativeTypeFormat(CreativeType creativeType) throws BaiduApiException{
		
		int sdkCreativeType = 0;
		switch (creativeType) {
		//@wuxing, 百度创意为0时, 转为5, 信息流广告
		case NO_TYPE: 	sdkCreativeType = 5; break;
		case TEXT:	  	sdkCreativeType = 1; break;	
		case IMAGE:	  	sdkCreativeType = 2; break;
		case TEXT_ICON: sdkCreativeType = 3; break;
		case VIDEO:
			throw new BaiduApiException(BaiduExpType.BAIDU_CREATIVETYPE_ERROR);
		default:
			break;
		}
		return sdkCreativeType;
	}
	
	/**
	 * 获取广告位类型
	 * @param fillingDataVO
	 * @return
	 */
	public final static int getAdslotType(FillingDataVO fillingDataVO){
		
		int adslotType = 1;
		int instl = fillingDataVO.getInstl(); // 是否是插屏的标志位
		int isSplash = fillingDataVO.getIs_splash(); // 是否是开屏的标志位
		
		if (0 != isSplash) {
			// 开屏广告位
			adslotType = 2;
		} else if (1 == instl) {
			// 插屏广告位
			adslotType = 3;
		} else if(null != fillingDataVO.getNativ()){
			//原生广告
			adslotType = 4;
		} else {
			// 横幅广告位
			adslotType = 1;
		}
		return adslotType;
	}
	
	
	public static void main(String[] args){
		
		System.out.println(versionCheck(null));
		
	}
	
}
