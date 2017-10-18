package com.gionee.ssp.service.req.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gionee.ssp.service.req.ConvertReqDeviceService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.ConnectionType;
import com.wk.model.adx.WKSSP.DeviceType;
import com.wk.model.adx.WKSSP.LocationType;
import com.wk.model.adx.WKSSP.OperatorType;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.utils.MD5Utils;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.vo.sdk.SdkDeviceVO;
import com.wk.ssp.vo.sdk.SdkGpsVO;
import com.wk.ssp.vo.sdk.SdkNetworkVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqDeviceServiceImpl implements ConvertReqDeviceService{
	/**
	 * @title: setDevice
	 * @description: 转换Device字段
	 * @param reqBuilder
	 *            ADXRequest字段
	 * @param sdkReq
	 *            SDK请求字段
	 * @throws Exception
	 */
	public void setDevice(Builder reqBuilder, SdkRequestVO sdkReq) throws Exception {

		WKSSP.Device.Builder deviceBuilder = WKSSP.Device.newBuilder();
		SdkDeviceVO device = sdkReq.getDevice();
		SdkNetworkVo networkVo = sdkReq.getNetwork();
		deviceBuilder.setDeviceType(DeviceType.valueOf(device.getDevice_type()));
		// 操作系统类型，非必填 默认android
		if (StringUtils.isNotBlank(device.getOs_type())) {

			deviceBuilder.setOsType(device.getOs_type().toLowerCase());
		}
		deviceBuilder.setOsVersion(device.getOs_version());
		deviceBuilder.setVendor(device.getVendor());
		deviceBuilder.setModel(device.getModel());
		deviceBuilder.setAndroidId(device.getAndroid_id());
		deviceBuilder.setAndroidIdMd5(MD5Utils.md5AsString(device.getAndroid_id()));
		if (ObjectUtils.isEmpty(device.getMac())) {
			deviceBuilder.setMac("");
		} else {
			deviceBuilder.setMac(device.getMac());
		}
		if (ObjectUtils.isEmpty(device.getUa())) {
            deviceBuilder.setUa("");
        } else {
            deviceBuilder.setUa(device.getUa());
        }
		deviceBuilder.setDpi(device.getDpi());
        deviceBuilder.setImei(device.getImei_md5());
        deviceBuilder.setImeiMd5(device.getImei_md5());

		// 转换运营商类型
		switch (networkVo.getCarrier()) {
		case 0:
			deviceBuilder.setCarrier(OperatorType.UNKNOWN_OPERATOR);
			break;
		case 1:
			deviceBuilder.setCarrier(OperatorType.CHINA_MOBILE);
			break;
		case 2:
			deviceBuilder.setCarrier(OperatorType.CHINA_UNICOM);
			break;
		case 3:
			deviceBuilder.setCarrier(OperatorType.CHINA_TELECOM);
			break;
		case 4:
			deviceBuilder.setCarrier(OperatorType.OTHER_OPERATOR);
			break;
		default:
			deviceBuilder.setCarrier(OperatorType.UNKNOWN_OPERATOR);
			break;
		}
		deviceBuilder.setCarrier(OperatorType.valueOf(networkVo.getCarrier()));
		deviceBuilder.setW(device.getW());
		deviceBuilder.setH(device.getH());

		ServletRequestAttributes respAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = respAttributes.getRequest();
		String ip = WKHttpUtils.getIpAddr(request);
		deviceBuilder.setIpv4(ip);

		// 转换网络连接类型
		switch (networkVo.getConnect_type()) {
		case 0:
			deviceBuilder.setConnectType(ConnectionType.CONNECTION_UNKNOWN);
			break;
		case 1:
			deviceBuilder.setConnectType(ConnectionType.ETHERNET);
			break;
		case 2:
			deviceBuilder.setConnectType(ConnectionType.WIFI);
			break;
		case 3:
			deviceBuilder.setConnectType(ConnectionType.CELL_UNKNOWN);
			break;
		case 4:
			deviceBuilder.setConnectType(ConnectionType.CELL_2G);
			break;
		case 5:
			deviceBuilder.setConnectType(ConnectionType.CELL_3G);
			break;
		case 6:
			deviceBuilder.setConnectType(ConnectionType.CELL_4G);
			break;
		default:
			deviceBuilder.setConnectType(ConnectionType.CONNECTION_UNKNOWN);
			break;
		}
		this.setGps(deviceBuilder, sdkReq);

		reqBuilder.setDevice(deviceBuilder);
	}
	/**
	 * @title: setGps
	 * @description: 转换GPS字段
	 * @param reqBuilder
	 *            ADXRequest字段
	 * @param sdkRes
	 *            SDK请求字段
	 */
	private void setGps(WKSSP.Device.Builder deviceBuilder, SdkRequestVO sdkRes) {

		SdkGpsVO gps = sdkRes.getGps();
		if (gps != null) {
			WKSSP.Geo.Builder gpsBuilder = WKSSP.Geo.newBuilder();
			gpsBuilder.setLat(gps.getLat());
			gpsBuilder.setLon(gps.getLon());
			gpsBuilder.setType(LocationType.GPS_LOCATION);

			deviceBuilder.setGeo(gpsBuilder);
		}
	}

}
