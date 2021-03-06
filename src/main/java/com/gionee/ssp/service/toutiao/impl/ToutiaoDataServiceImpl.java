package com.gionee.ssp.service.toutiao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adx.impl.BaseAdxDataServiceImpl;
import com.gionee.ssp.service.cp.CheckCpService;
import com.gionee.ssp.service.toutiao.ToutiaoDataService;
import com.gionee.ssp.utils.toutiao.ToutiaoApiUtil;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest.AdSlot;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest.App;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest.Device;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidRequest.Geo;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidResponse;
import com.wk.ssp.mvc.toutiao.JinritoutiaoProto.BidResponse.Ad;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.AdTimeVO;
import com.wk.ssp.vo.sdk.AdType;
import com.wk.ssp.vo.sdk.SdkDeviceVO;
import com.wk.ssp.vo.sdk.SdkGpsVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkNetworkVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description 今日头条数据处理层
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class ToutiaoDataServiceImpl extends BaseAdxDataServiceImpl implements ToutiaoDataService {
	Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 校验cp服务层
	 */
	@Autowired
	CheckCpService checkCpService;
	@Override	
	public BidRequest reqInfoBuilder(SdkRequestVO req, FillingDataVO vo, AdxAdslotInfoVo adx) {
		
		BidRequest.Builder request_builder = BidRequest.newBuilder();
		request_builder.setRequestId(ToutiaoApiUtil.requestIdBuilder("", ""));
		//设置api_version的值
		request_builder.setApiVersion(req.getApi_version());
		//设置app值,通过调用app_message()返回App实体
		request_builder.setApp(app_message(adx.getAppId(), adx.getAppPackage(), req.getApp().getApp_version()));
		//设置geo的数据
		request_builder.setGeo(geo_message(req.getGps()));
		//设置device的数据
		request_builder.setDevice(device_message(req.getDevice(),req.getNetwork()));
		//添加一个adslot信息
		request_builder.addAdslots(adslot_message(req, vo, adx.getAdslotId()));
		return request_builder.build();
	}



	@Override
	public SdkResponseVO rspInfoHandler(BidResponse mobadsResponse, FillingDataVO fillingDataVO,
			SdkRequestVO req) throws Exception {
		SdkResponseVO rsp = new SdkResponseVO();
		if(mobadsResponse == null){
			rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
		}
		
		List<SdkResponseAdVO> sdkRspAds = new ArrayList<SdkResponseAdVO>();
		List<Ad> ads = mobadsResponse.getAdsList();
		SdkResponseAdVO vo;
		for(Ad ad : ads){
			vo = new SdkResponseAdVO();
			vo.setAdslot_id(req.getAdslot().getAdslot_id());
			AdTimeVO ad_time = new AdTimeVO();
			ad_time.setForce(force_time);
			ad_time.setSkip(skip_time);
			ad_time.setDelay(delay_time);
			ad_time.setExposure(exposure_time);
			//根据广告位类型不同的特殊处理
			if(AdType.NATIVE.getType() == getAdType(fillingDataVO)){
				//广告类型为原生, 创意类型为信息流广告
				vo.setAd_type(AdType.NATIVE.getType());
				vo.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
				//设置原生字段
				SdkNativeVO nativeVo = assembleNative(mobadsResponse, ad, fillingDataVO.getNativ().get(0).getType());
				vo.setNativ(nativeVo);
			}else{
				vo.setAd_type(getAdType(fillingDataVO));
				vo.setCreative_type(CommonConstant.CreativeType.IMG.getValue());
				//设置音乐的广告的今日头条的曝光时间
				if(checkCpService.isMusic(req.getApp().getApp_id(), req.getAdslot().getAdslot_id())){
					ad_time.setExposure(music_toutiao_exposure);
				}
			}
			vo.setAd_time(ad_time);
			vo.setAd_cp(AdxType.TOUTIAO.getAdx());	//今日头条
			vo.setAd_expire(mobadsResponse.getExpirationTime());
			
			//交互类型 1-展示类广告 2-下载类广告
			vo.setInteraction_type(ad.getCreative().getInteractionType().getNumber());
			vo.setBundle(ad.getCreative().getPackageName()); //下载类广告应用包名
			
			vo.setImgurl(ad.getCreative().getImage().getUrl()); //物料 URL
			vo.setW(ad.getCreative().getImage().getWidth()); //宽（非必输）
			vo.setH(ad.getCreative().getImage().getHeight()); //高（非必输）
			vo.setClkurl(ad.getCreative().getTargetUrl()); //点击跳转地址
			//监播行为
			List<String> imps_trackers=new ArrayList<String>(ad.getCreative().getShowUrlList());
			vo.setImptrackers(imps_trackers); //曝光地址,必须是ArrayList
			
			List<String> click_trackers=new ArrayList<String>(ad.getCreative().getClickUrlList());
			vo.setClktrackers(click_trackers); //点击监播地址
			
//			sdkResAdVO.setDwnlst(); //下载开始监播
			sdkRspAds.add(vo);
	    }
		//当没有广告时, 返回null
		if(sdkRspAds.size() == 0){
			rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
		}
		rsp.setAdms(sdkRspAds);
		rsp.setExpiration_time(mobadsResponse.getExpirationTime()); //过期时间戳
		rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL); //自动请求广告的时间间隔
		return rsp;
	}
	
	private App app_message(String adxAppId, String appPackage, String appVersion) {
		//创建App Builder
		App.Builder app = App.newBuilder();
		
		app.setId(adxAppId);
		//设置app应用名
		app.setName(appPackage);
		//app应用的版本
		app.setVersion(appVersion);
		//设置is_paid_app
		app.setIsPaidApp(false);

		return app.build();
	}
	
	private Geo geo_message(SdkGpsVO gps){
		/*fill in geo message*/

		//创建geo Builder
		Geo.Builder geo = Geo.newBuilder();
		//设置latitude
		geo.setLatitude((float)gps.getLat());
		//设置longitude
		geo.setLongitude((float)gps.getLon());

		return geo.build();
	}

	private Device device_message(SdkDeviceVO dev,SdkNetworkVo network){
		//创建 Device Builder
		Device.Builder device = Device.newBuilder();
		//设置did
		device.setDid(dev.getImei_md5());
		//设置操作系统版本os_version
		device.setOsVersion(dev.getOs_version());
		//设置设备类型type
		device.setType(Device.DeviceType.PHONE);
		device.setVendor("GIONEE");
		device.setModel(dev.getModel());
		//设置设备的网络类型conn_type
		/** 网络连接类型  0：未知连接，1：以太网，2：WiFi，3：未知蜂窝网络，4:2G，5:3G，6:4G**/
		if(network.getConnect_type()==0){
		device.setConnType(Device.ConnectionType.CONN_UNKNOWN);
		}else if(network.getConnect_type()==2){
		device.setConnType(Device.ConnectionType.WIFI);
		}else if(network.getConnect_type()==4){
		device.setConnType(Device.ConnectionType.MOBILE_2G);
		}else if(network.getConnect_type()==5){
		device.setConnType(Device.ConnectionType.MOBILE_3G);
		}else if(network.getConnect_type()==6){
		device.setConnType(Device.ConnectionType.MOBILE_4G);
		}
		//设置操作系统类型os
		device.setOs(Device.OsType.ANDROID);
		device.setIp(network.getIp());
		//设置浏览器信息ua是空
		//device.setUa(dev.getUa());
		//设置设备厂商vendor为空
		//device.setVendor("");
		return device.build();
	}

	private AdSlot adslot_message(SdkRequestVO requestVo,FillingDataVO fillVo, String adslotId) {
		/* make a slot message*/
		//读出尺寸，现在只读一个！
		
		//创建AdSlot Builder
		AdSlot.Builder adslot = AdSlot.newBuilder();
		
		adslot.setId(adslotId);
		//设置广告类型adtype
		adslot.setAdtype(ToutiaoApiUtil.getToutiaolotType(fillVo));
		//设置广告展现位置pos
		adslot.setPos(AdSlot.Position.FULLSCREEN);
		AdSlot.Size.Builder size = AdSlot.Size.newBuilder();
		//设置宽高: 锁屏的广告用特殊处理, 原生广告用redis中的nativ信息, 横幅用banner信息, 其他的用广告位信息
		if(checkCpService.isLock(requestVo.getApp().getApp_id(), requestVo.getAdslot().getAdslot_id())){
			size.setHeight(1920);
			size.setWidth(1080);
		}else if(AdType.NATIVE.getType() == getAdType(fillVo)){
			size.setHeight(fillVo.getNativ().get(0).getH());
			size.setWidth(fillVo.getNativ().get(0).getW());
		}else if(AdType.BANNER.getType() == getAdType(fillVo)){
			size.setHeight(fillVo.getBanner().getH());
			size.setWidth(fillVo.getBanner().getW());
		}else {
			size.setHeight(requestVo.getAdslot().getAdslot_h());
			size.setWidth(requestVo.getAdslot().getAdslot_w());
		}
		adslot.addAcceptedSize(size.build());
		return adslot.build();
	}
	
	private SdkNativeVO assembleNative(BidResponse mobadsResponse, Ad ad, int type){
		//组装原生字段
		SdkNativeVO nativeVo = new SdkNativeVO();
		List<String> imgUrls = new ArrayList<String>();
		imgUrls.add(ad.getCreative().getImage().getUrl());
		nativeVo.setImgurl(imgUrls);
		nativeVo.setTitle(ad.getCreative().getTitle());
		nativeVo.setType(type);	//根据广告位类型填充, 1:组图, 2:小图, 3:大图
		return nativeVo;
	}
}
