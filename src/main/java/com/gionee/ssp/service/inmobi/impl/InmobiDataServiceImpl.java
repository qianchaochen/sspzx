package com.gionee.ssp.service.inmobi.impl;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adx.impl.BaseAdxDataServiceImpl;
import com.gionee.ssp.service.inmobi.InmobiDataService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.MD5Utils;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.AdTimeVO;
import com.wk.ssp.vo.sdk.AdType;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description 
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class InmobiDataServiceImpl extends BaseAdxDataServiceImpl implements InmobiDataService {
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public JSONObject reqInfoBuilder(SdkRequestVO vo, String adxAdslotId, String appPackage) {
		JSONObject json = null;
		try {
			json = request(vo, adxAdslotId, appPackage);
		} catch (Exception e) {
			log.error("InmobiDataServiceImp Get Request JSON is error",e);
			return null;
		}
		return json;
	}

	
	private JSONObject request(SdkRequestVO vo, String adxAdslotId, String appPackage) throws Exception{
		JSONObject json = new JSONObject();
		
		JSONObject json_app = new JSONObject();
		json_app.put("id", adxAdslotId);
		json_app.put("bundle", appPackage);
		
		JSONObject json_imp = new JSONObject();
		JSONObject json_imp_native = new JSONObject();
		json_imp_native.put("layout",0);
		json_imp.put("native",json_imp_native);
		json_imp.put("trackertype","url_ping");
		json_imp.put("secure", 0);
		JSONObject json_imp_ext = new JSONObject();
		json_imp_ext.put("ads",1);
		json_imp.put("ext", json_imp_ext);
		JSONObject json_device = new JSONObject();
		json_device.put("md5_imei",vo.getDevice().getImei_md5());
		json_device.put("o1",getSHA(vo.getDevice().getAndroid_id().toLowerCase()));
		json_device.put("um5",MD5Utils.md5AsString(vo.getDevice().getAndroid_id().toLowerCase()));
		json_device.put("ua",vo.getDevice().getWeb_ua());
		json_device.put("ip",vo.getNetwork().getIp());
		JSONObject json_device_geo = new JSONObject();
		json_device_geo.put("accu",0);
		json_device_geo.put("lat",vo.getGps().getLat());
		json_device_geo.put("lon",vo.getGps().getLon());
		
		json_device.put("geo",json_device_geo);
		json_device.put("carrier","");
		int connectiontype = 0;
		if(vo.getNetwork().getConnect_type() >= 1 && vo.getNetwork().getConnect_type() <= 6){
			connectiontype = vo.getNetwork().getConnect_type();
		}
		json_device.put("connectiontype",connectiontype);
		JSONObject json_device_ext = new JSONObject();
		json_device_ext.put("orientation",1);
		json_device.put("ext",json_device_ext);
		
		JSONObject json_ext = new JSONObject();
		json_ext.put("responseformat","json");
		
		json.put("app",json_app);
		json.put("imp",json_imp);
		json.put("device",json_device);
		json.put("ext",json_ext);
		return json;
	}


	@Override
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse,
			SdkRequestVO req, FillingDataVO vo) throws Exception {

		SdkResponseVO rsp = new SdkResponseVO();
		List<SdkResponseAdVO> sdkRspAds = new ArrayList<SdkResponseAdVO>();
		JSONArray json_ads = mobadsResponse.getJSONArray("ads");
 		if(json_ads.length()==0){
 			rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
		}
		SdkResponseAdVO sdkResAdVO;
 		JSONObject json_=json_ads.getJSONObject(0);
 		
 		String pubcontent=json_.getString("pubContent");
 		String pub=decodeBase64(pubcontent);
 		JSONObject js = new JSONObject(pub);
 		JSONObject json_img = js.getJSONObject("screenshots");
 		int w = json_img.getInt("width");
 		int h = json_img.getInt("height");
		sdkResAdVO = new SdkResponseAdVO();
		sdkResAdVO.setAdslot_id(req.getAdslot().getAdslot_id());
		
		AdTimeVO ad_time = new AdTimeVO();
		ad_time.setForce(force_time);
		ad_time.setSkip(skip_time);
		ad_time.setDelay(delay_time);
		ad_time.setExposure(exposure_time);
		sdkResAdVO.setAd_time(ad_time);
		
		//根据广告位类型不同的特殊处理
		if(AdType.NATIVE.getType() == getAdType(vo)){
			//广告类型为原生, 创意类型为信息流广告
			sdkResAdVO.setAd_type(AdType.NATIVE.getType());
			sdkResAdVO.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
			//设置原生字段
			SdkNativeVO nativeVo = assembleNative(js, json_img, vo.getNativ().get(0).getType());
			sdkResAdVO.setNativ(nativeVo);
		}else{
			//其他类型的广告
			sdkResAdVO.setAd_type(getAdType(vo));
			sdkResAdVO.setCreative_type(CommonConstant.CreativeType.IMG.getValue());
		}
		sdkResAdVO.setAd_cp(AdxType.INMOBI.getAdx());	//inmobi
		sdkResAdVO.setInteraction_type(CommonConstant.SDKInteractionType.WEB.getValue());
		//图文混合类广告单独传输MIXVO对象
		sdkResAdVO.setImgurl(json_img.getString("url")); //物料 URL
		sdkResAdVO.setW(w); //宽（非必输）
		sdkResAdVO.setH(h); //高（非必输）
		sdkResAdVO.setClkurl(json_.getString("landingPage")); //点击跳转地址
		
 		JSONObject json_Tack = json_.getJSONObject("eventTracking");
   		JSONObject json_18 = json_Tack.getJSONObject("18");
   		JSONArray json_url_18 = json_18.getJSONArray("urls");
   		List<String> vmurl=new ArrayList<String>();
		for (int j = 0 ;j < json_url_18.length(); j++){
			String url=json_url_18.getString(j);
			String url_rep="";
			if(url.indexOf("$TS")!=-1){
				url_rep=url.replace("$TS", String.valueOf(System.currentTimeMillis()));
				vmurl.add(url_rep);
			}else{
				vmurl.add(url);
			}
		}
   		
   		JSONObject json_8 = json_Tack.getJSONObject("8");
   		JSONArray json_url_8 = json_8.getJSONArray("urls");
		List<String> cmurl=new ArrayList<String>();
		for (int j = 0 ;j < json_url_8.length(); j++){
			String url  =  json_url_8.getString(j);
			String url_rep="";
			if(url.indexOf("$TS")!=-1){
				
				url_rep=url.replace("$TS", String.valueOf(System.currentTimeMillis()));
				cmurl.add(url_rep);
			}else{
				cmurl.add(url);
			}
		}
		//监播行为
		sdkResAdVO.setImptrackers(vmurl); //曝光地址,必须是ArrayList
		
		sdkResAdVO.setClktrackers(cmurl); //点击监播地址
//			sdkResAdVO.setDwnlst(); //下载开始监播
		
		sdkRspAds.add(sdkResAdVO);
		rsp.setRequest_id(mobadsResponse.getString("requestId"));
		rsp.setAdms(sdkRspAds);
		rsp.setExpiration_time(11111l); //过期时间戳
		rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL); //自动请求广告的时间间隔
		return rsp;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String decodeBase64(String input) throws Exception{  
	    Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	    Method mainMethod= clazz.getMethod("decode", String.class);  
	    mainMethod.setAccessible(true);  
	     Object retObj=mainMethod.invoke(null, input);  
	     String str2 = new String((byte[])retObj);
	     return str2;  
	} 
	
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
		    'a', 'b', 'c', 'd', 'e', 'f' };
	
	private String getSHA(String inStr){
		byte[] inStrBytes = inStr.getBytes();
		try {
			MessageDigest MD = MessageDigest.getInstance("sha-1");
			MD.update(inStrBytes);
			byte[] mdByte = MD.digest();
			char[] str = new char[mdByte.length * 2];
			int k = 0;
			for(int i=0;i<mdByte.length;i++) {
				byte temp = mdByte[i];
				str[k++] = hexDigits[temp >>> 4 & 0xf];
				str[k++] = hexDigits[temp & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 组装原生广告
	 * @author: wuxing
	 * @date: 2017年4月18日 上午10:44:08
	 *
	 */
	private SdkNativeVO assembleNative(JSONObject ad, JSONObject img, int type){
		/**
		 * inmobi返回素材json样例
		 *  {"title":"Viva Slots Las Vegas - Fr","description":"Install Viva Slots Vegas Today and start spinning
			today!Viva Slots Vegas has the most realistic sl","icon":{"width":300,"height":300,"url":"http://uacassets.inmobicdn.net/1017064541-us-1454560400302","aspectRatio":1.0},"screenshots":{"width":
			480,"height":320,"url":"http://r.edge.inmobicdn.net/FileData/b5c70b4ba63e-401c-809f-84a4a0357dae.png","aspectRatio":1.5},"landingURL":"https://itunes.apple.com/cn/
			app/viva-slots-vegas-casino/id1017064541?mt=8","cta":"install","rating":5.0}
		 *
		 */
		//组装原生字段
		SdkNativeVO nativeVo = new SdkNativeVO();
		List<String> imgUrls = new ArrayList<String>();
		imgUrls.add(img.getString("url"));
		nativeVo.setImgurl(imgUrls);
		nativeVo.setTitle(ad.getString("title"));
		nativeVo.setType(type);	//根据广告位类型填充, 1:组图, 2:小图, 3:大图
		return nativeVo;
	}
}
