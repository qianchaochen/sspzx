package com.gionee.ssp.service.baidu.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.gionee.ssp.model.BaiduMobads.Ad;
import com.gionee.ssp.model.BaiduMobads.AdSlot;
import com.gionee.ssp.model.BaiduMobads.App;
import com.gionee.ssp.model.BaiduMobads.App.Builder;
import com.gionee.ssp.model.BaiduMobads.Device;
import com.gionee.ssp.model.BaiduMobads.Device.DeviceType;
import com.gionee.ssp.model.BaiduMobads.Device.OsType;
import com.gionee.ssp.model.BaiduMobads.Gps;
import com.gionee.ssp.model.BaiduMobads.Gps.CoordinateType;
import com.gionee.ssp.model.BaiduMobads.MaterialMeta;
import com.gionee.ssp.model.BaiduMobads.MobadsRequest;
import com.gionee.ssp.model.BaiduMobads.MobadsResponse;
import com.gionee.ssp.model.BaiduMobads.Network;
import com.gionee.ssp.model.BaiduMobads.Network.ConnectionType;
import com.gionee.ssp.model.BaiduMobads.Network.OperatorType;
import com.gionee.ssp.model.BaiduMobads.Size;
import com.gionee.ssp.model.BaiduMobads.Tracking;
import com.gionee.ssp.model.BaiduMobads.Tracking.TrackingEvent;
import com.gionee.ssp.model.BaiduMobads.UdId;
import com.gionee.ssp.model.BaiduMobads.Version;
import com.gionee.ssp.service.baidu.BaiduDataService;
import com.gionee.ssp.utils.baidu.BaiduApiUtil;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.google.protobuf.ByteString;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillBannerVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkAdslotVO;
import com.wk.ssp.vo.sdk.SdkDeviceVO;
import com.wk.ssp.vo.sdk.SdkGpsVO;
import com.wk.ssp.vo.sdk.SdkMixVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkNetworkVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 *         2016年12月16日
 */
@Service
public class BaiduDataServiceImp extends BaseBaiduServiceImpl implements BaiduDataService {

	@Override
	public MobadsRequest reqInfoBuilder(SdkRequestVO vo, FillingDataVO fillVo,AdxAdslotInfoVo adx) {

		MobadsRequest.Builder builder = MobadsRequest.newBuilder();
		builder.setRequestId(BaiduApiUtil.requestIdBuilder(adx.getAppId(), adx.getAdslotId())); // 请求ID
		builder.setApiVersion(verBuilder(baidu_api_version)); // 百度API版本
		builder.setApp(appBuilder(adx.getAppId(), vo.getApp().getApp_version(), fillVo.getBundle()));
		builder.setAdslot(adslotBuilder(vo.getAdslot(), adx.getAdslotId()));
		builder.setDevice(deviceBuilder(vo.getDevice()));
		builder.setNetwork(networkBuilder(vo.getNetwork()));
		builder.setGps(gpsBuilder(vo.getGps()));

		return builder.build();
	}

	@Override
	public SdkResponseVO rspInfoHandler(MobadsResponse mobadsResponse, FillingDataVO fillingDataVO,
			SdkRequestVO req) throws BaiduApiException {

		SdkResponseVO rsp = new SdkResponseVO();

		if (0 != mobadsResponse.getErrorCode()) {
			rsp.setError_code(Errors.NO_CONTENT);
			rsp.setAdms(new ArrayList<>());
			return rsp;
		}

		rsp.setError_code(Constant.SDK_RSP_NO_ERROR);

		List<SdkResponseAdVO> sdkRspAds = new ArrayList<SdkResponseAdVO>();
		List<Ad> ads = mobadsResponse.getAdsList();
		Iterator<Ad> adIt = ads.iterator();
		SdkResponseAdVO vo;
		Ad ad;
		while (adIt.hasNext()) {
			ad = adIt.next();

			List<MaterialMeta> metas = ad.getMetaGroupList();
			if (metas == null || metas.size() == 0) {
				rsp.setError_code(Errors.NO_CONTENT);
				rsp.setAdms(new ArrayList<>());
				return rsp;
			}
			MaterialMeta meta = metas.get(0);
			vo = new SdkResponseAdVO();
			vo.setAdslot_id(req.getAdslot().getAdslot_id());
			vo.setAd_type(BaiduApiUtil.getAdslotType(fillingDataVO));
			// 交互类型 1-展示类广告 2-下载类广告
			vo.setInteraction_type(meta.getInteractionType().getNumber());
			vo.setBundle(meta.getAppPackage()); // 下载类广告应用包名

			vo.setCreative_type(BaiduApiUtil.creativeTypeFormat(meta.getCreativeType())); // 创意类型
			// 图文混合类广告单独传输MIXVO对象

			// 百度返回的creative_type不准
			// 已与百度沟通，目前我方无法根据返回数据判断，我方无法处理

			if (vo.getCreative_type() == CommonConstant.CreativeType.MIX.getValue()) {
				SdkMixVO sdkMixVO = sdkMIxVOBuilder(meta, fillingDataVO.getBanner());
				vo.setMix(sdkMixVO); 	// 图文混合类创意
			
			} else if (vo.getCreative_type() == CommonConstant.CreativeType.NATIVE.getValue()
					|| CommonConstant.AD_TYPE.NATIVE.getValue() == vo.getAd_type()) {
				vo.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue()); //信息流广告
				SdkNativeVO sdkNativeVO = sdkNativeVOBuilder(meta);
				vo.setNativ(sdkNativeVO);// 信息流广告
			} else {
				// 由于百度可能返回纯文字的广告，因此可能没有图片
				if (meta.getImageSrcCount() != 0) {
					vo.setImgurl(meta.getImageSrc(0)); // 物料 URL
				}
			}
			vo.setW(meta.getMaterialWidth()); // 宽（非必输）
			vo.setH(meta.getMaterialHeight()); // 高（非必输）
			vo.setClkurl(meta.getClickUrl()); // 点击跳转地址

			// 监播行为
			List<Tracking> tackings = ad.getAdTrackingList();

			ArrayList<String> imptrackersList = new ArrayList<String>();
			imptrackersList.addAll(ad.getMaterialMeta().getWinNoticeUrlList());
			vo.setImptrackers(imptrackersList); // 曝光地址,必须是ArrayList

			vo.setClktrackers(getTrackingUrls(tackings, TrackingEvent.AD_CLICK)); // 点击监播地址
			vo.setDwnltrackers(getTrackingUrls(tackings, TrackingEvent.APP_AD_DOWNLOAD)); // 下载监播
			vo.setIntltrackers(getTrackingUrls(tackings, TrackingEvent.APP_AD_INSTALL)); // 安装监播
			vo.setActvtrackers(getTrackingUrls(tackings, TrackingEvent.APP_AD_ACTIVE)); // 激活监播
			// sdkResAdVO.setDwnlst(); //下载开始监播
			vo.setAd_cp(AdxType.BAIDU.getAdx());
			sdkRspAds.add(vo);
		}
		rsp.setAdms(sdkRspAds);
		rsp.setExpiration_time(mobadsResponse.getExpirationTime()); // 过期时间戳
		rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL); // 自动请求广告的时间间隔
		rsp.setGet_ad_in_same_view_times(Constant.VIEW_INTERVAL_TIMES);// 重复请求广告次数

		return rsp;
	}

	/**
	 * SDK返回图文混合类创意构建
	 * 
	 * @param meta
	 *            百度返回广告元数据对象
	 * @param fillBannerVO
	 *            redis中广告位填充信息
	 * 
	 *            modified by zhengk 20170814 主标题以adtitle为准 副标题取desc
	 *            如果iconSrc为空，则取imgsrc 即：adTitle、iconSrc优先
	 * @return
	 */
	private SdkMixVO sdkMIxVOBuilder(MaterialMeta meta, FillBannerVO fillBannerVO) {

		SdkMixVO sdkMixVO = new SdkMixVO();

		String title = meta.getAdTitle().trim(); // 以Ad title 为准
		if (StringUtils.isEmpty(title)) {
			title = meta.getTitle().toStringUtf8();
		}
		sdkMixVO.setTitle(title); // 主标题

		String desc = "";
		if (meta.getDescriptionCount() > 0) {
			desc = meta.getDescription(0).toStringUtf8();
		}
		sdkMixVO.setSub_title(desc); // 子标题

		String imgUrl = meta.getIconSrc(0).trim(); // 如果ICON为空，则取广告图片地址
		if (StringUtils.isEmpty(imgUrl)) {
			imgUrl = meta.getImageSrc(0);
		}
		sdkMixVO.setImgurl(imgUrl); // 创意展示图片URL

		sdkMixVO.setAcimgurl(""); // 创意动作图片地址
		sdkMixVO.setBg_color(fillBannerVO.getBg_color()); // 背景色
		sdkMixVO.setText_color(fillBannerVO.getTx_color()); // 文字颜色

		return sdkMixVO;
	}

	/**
	 * build nativeVo
	 * 
	 * @author: wuxing
	 * @date: 2016年12月23日 下午2:25:20
	 *
	 *        modified by zhengk由于发现原生广告没有图片的问题，因此对获取图片方法进行调整，是否解决问题有待观察
	 */
	private SdkNativeVO sdkNativeVOBuilder(MaterialMeta meta) {
		SdkNativeVO sdkNativeVO = new SdkNativeVO();

		// 判断imageSrcList是否为空，如果为空，则取imageSrc
		List<String> imgs = new ArrayList<>();
		imgs.addAll(meta.getImageSrcList());

		if (CollectionUtils.isEmpty(imgs)) {
			for (int i = 0; i < meta.getImageSrcCount(); i++) {
				imgs.add(meta.getImageSrc(i));
			}
		}
		sdkNativeVO.setImgurl(imgs);

		sdkNativeVO.setTitle(StringUtils.isNotEmpty(meta.getAdTitle().trim()) ? meta.getAdTitle().trim()
				: (2 == meta.getInteractionType().getNumber() ? meta.getBrandName() : meta.getTitle().toStringUtf8()));
		sdkNativeVO.setSource(meta.getBrandName());
		sdkNativeVO.setSub_title(meta.getDescription(0).toStringUtf8());
		sdkNativeVO.setType(imgs.size() > 1 ? 1 : 3);
		return sdkNativeVO;
	}

	/**
	 * 应用对象构建
	 * 
	 * @param appId
	 *            应用id
	 * @param appVer
	 *            应用版本号
	 * @param packageName
	 *            应用包名
	 * @return
	 */
	private Builder appBuilder(String appId, String appVer, String packageName) {

		App.Builder appBuilder = App.newBuilder();
		appBuilder.setAppId(appId);
		appBuilder.setAppVersion(verBuilder(appVer)); // 应用版本（？）
		appBuilder.setAppPackage(packageName);
		appBuilder.setChannelId(""); // 渠道ID
		return appBuilder;
	}

	/**
	 * 版本对象构建
	 * 
	 * @param version
	 *            版本号（^[\\d]+\\.[\\d]+(\\.[\\d]+$){0,1}$）
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.Version.Builder verBuilder(String version) {

		version = BaiduApiUtil.versionCheck(version);
		String[] versions = version.split("\\.");
		Version.Builder verBuilder = Version.newBuilder();
		try {
			verBuilder.setMajor(Integer.parseInt(versions[0]));
			verBuilder.setMinor(Integer.parseInt(versions[1]));
			verBuilder.setMicro(Integer.parseInt(versions[2]));
		} catch (Exception e) {
			verBuilder.setMajor(1);
			verBuilder.setMinor(0);
			verBuilder.setMicro(0);
		}
		return verBuilder;
	}

	/**
	 * 百度请求广告位对象构建
	 * 
	 * @param adslot
	 *            SDK请求广告位对象
	 * @param adslotId
	 *            百度广告位ID
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.AdSlot.Builder adslotBuilder(SdkAdslotVO adslot, String adslotId) {

		AdSlot.Builder adslotBuilder = AdSlot.newBuilder();
		adslotBuilder.setAdslotId(adslotId);
		adslotBuilder.setAdslotSize(sizeBuilder(adslot.getAdslot_w(), adslot.getAdslot_h()));
		return adslotBuilder;
	}

	/**
	 * 尺寸对象构建
	 * 
	 * @param w
	 * @param h
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.Size.Builder sizeBuilder(int w, int h) {

		Size.Builder sizeBuilder = Size.newBuilder();
		sizeBuilder.setWidth(w);
		sizeBuilder.setHeight(h);
		return sizeBuilder;
	}

	/**
	 * 设备对象构建
	 * 
	 * @param device
	 *            SDK请求设备对象
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.Device.Builder deviceBuilder(SdkDeviceVO device) {

		Device.Builder deviceBuilder = Device.newBuilder();
		deviceBuilder.setDeviceType(DeviceType.PHONE);
		deviceBuilder.setOsType(OsType.ANDROID); // Android
		deviceBuilder.setOsVersion(verBuilder(device.getOs_version()));
		deviceBuilder.setVendor(ByteString.copyFromUtf8("GIONEE")); // 设备厂商
		deviceBuilder.setModel(ByteString.copyFromUtf8(device.getModel())); // 机型
		deviceBuilder.setScreenSize(sizeBuilder(device.getW(), device.getH())); // 设备屏幕尺寸
		deviceBuilder.setUdid(udidBuilder(device)); // 设备唯一识别码
		return deviceBuilder;
	}

	/**
	 * 设备唯一识别码对象构建
	 * 
	 * @param device
	 *            SDK请求设备对象
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.UdId.Builder udidBuilder(SdkDeviceVO device) {

		UdId.Builder udidBuilder = UdId.newBuilder();
		udidBuilder.setImei(device.getImei_md5()); // imei
		udidBuilder.setAndroidId(device.getAndroid_id()); // Android 设备系统 ID
//		if(StringUtils.isEmpty(device.getMac())){
//			udidBuilder.setMac(""); // 设备 WiFi 网卡 MAC地址
//		}else{
//			udidBuilder.setMac(device.getMac()); // 设备 WiFi 网卡 MAC地址
//		}
		return udidBuilder;
	}

	/**
	 * 网络对象构建
	 * 
	 * @param network
	 *            SDK请求网络对象
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.Network.Builder networkBuilder(SdkNetworkVo network) {

		Network.Builder networkBuilder = Network.newBuilder();
		networkBuilder.setIpv4(network.getIp());
		networkBuilder
				.setConnectionType(ConnectionType.forNumber(BaiduApiUtil.connectTypeFormat(network.getConnect_type()))); // 连接类型
		networkBuilder.setOperatorType(OperatorType.forNumber(network.getCarrier())); // 运营商
																						// ID
																						// SDK上送运营商分类与百度一直
		// 生产环境发现sdk上传的celluar_id=null，此处会报错。将null设置给protobuf的string类型字段会出错
		networkBuilder.setCellularId(StringUtils.isEmpty(network.getCellular_id()) ? "" : network.getCellular_id()); // 基站
																														// ID
		return networkBuilder;
	}

	/**
	 * GPS对象构建
	 * 
	 * @param gps
	 *            SDK请求GPS对象
	 * @return
	 */
	private com.gionee.ssp.model.BaiduMobads.Gps.Builder gpsBuilder(SdkGpsVO gps) {

		// 因为百度只定义了123，如果SDK传0或其他值，此处就会报错，注：生产环境发现大量这个错误
		List<Integer> type = Arrays.asList(1, 2, 3);

		if (!type.contains(gps.getCoordinate_type())) {
			gps.setCoordinate_type(1);
		}
		Gps.Builder gpsBuilder = Gps.newBuilder();
		gpsBuilder.setCoordinateType(CoordinateType.forNumber(gps.getCoordinate_type())); // GPS
																							// 坐标类型
																							// SDK上送坐标分类与百度一直
		gpsBuilder.setLongitude(gps.getLon()); // GPS 坐标经度
		gpsBuilder.setLatitude(gps.getLat()); // GPS 坐标纬度
		gpsBuilder.setTimestamp((int) gps.getTimestamp()); // 时间戳
		return gpsBuilder;
	}

	/**
	 * 提取广告监播地址
	 * 
	 * @param trackings
	 *            监播地址集合
	 * @param event
	 *            监播事件
	 * @return
	 */
	private List<String> getTrackingUrls(List<Tracking> trackings, TrackingEvent event) {

		List<String> urls = new ArrayList<String>();

		if (trackings == null || trackings.size() == 0) {
			return urls;
		}
		Iterator<Tracking> it = trackings.iterator();
		while (it.hasNext()) {
			Tracking tracking = it.next();
			if (event == tracking.getTrackingEvent()) {
				urls.addAll(tracking.getTrackingUrlList());
			}
		}
		return urls;
	}
}
