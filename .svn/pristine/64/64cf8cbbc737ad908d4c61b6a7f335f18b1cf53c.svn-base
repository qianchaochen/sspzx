package com.wk.ssp.vo.sdk;

import javax.validation.constraints.NotNull;

import com.gionee.common.vo.BaseVo;

/**
 * @description: gps信息
 */
public class SdkGpsVO extends BaseVo{

	/** 坐标类型 1：全球卫星定位系统坐标系，2：国家测绘局坐标系，3：百度坐标系，4：其他坐标系 **/
	@NotNull(message = "106000")
	private int coordinate_type;
	
	/** 经度 **/
	@NotNull(message = "106010")
	private double lon;
	
	/** 纬度 **/
	@NotNull(message = "106020")
	private double lat;
	
	/** 时间戳 **/
	@NotNull(message = "106030")
	private long timestamp;

	public int getCoordinate_type() {
		return coordinate_type;
	}

	public void setCoordinate_type(int coordinate_type) {
		this.coordinate_type = coordinate_type;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
