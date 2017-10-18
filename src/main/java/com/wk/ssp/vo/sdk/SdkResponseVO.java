package com.wk.ssp.vo.sdk;

import java.util.List;

import com.gionee.common.vo.BaseVo;
import com.wk.ssp.mvc.Constant;

/**
 * @description:sdk响应字段
 */
public class SdkResponseVO extends BaseVo{

	private String request_id;
	/** 错误码 **/
	private int error_code;
	
	/** 创意 **/
	private SdkResponseAdVO ads;
	
	/** 创意，返回多条 **/
	private List<SdkResponseAdVO> adms;
	
    /** 同一view下，自动请求广告的时间间隔, 默认是3600s **/
    private int get_ad_in_same_view_interval = 3600;
    
    /** 同一view下，重复请求广告次数, 默认是3次 **/
    private int get_ad_in_same_view_times = 3;
    
    /** 过期时间 **/
    private long expiration_time = Constant.AD_EXPIRATION_TIME;

	/** 自升级开关。1：开， 0：关 **/
	private int swich;
	
	/** 下载二次确认。1：开， 0：关 **/
	private int dwlconfirm;
	
        /** logo开关 **/
	private int lg_swh;

        /** 崩溃日志开关 **/
	private int brkdwn;
	/**
	 * 0:非静默安装
	 * 1：静默安装
	 */
	private int silent_install;
	
	/**
	 * 多少天没有广告
	 */
	private int no_ad_days;
	//单个用户（imei）一天内看到广告的次数。默认值0，表示不限制。（注：以发送展示监播为准。）
	private int show_ad_times_one_day;
	//单个用户看到两次广告之间的时间间隔，以分钟为单位。默认值0，表示不限制。（注：以广告请求返回数为准。）
	private int show_ad_interval;

	public int getSwich() {
		return swich;
	}

	public void setSwich(int swich) {
		this.swich = swich;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public SdkResponseAdVO getAds() {
		return ads;
	}

	public void setAds(SdkResponseAdVO ads) {
		this.ads = ads;
	}

	public long getExpiration_time() {
		return expiration_time;
	}

	public void setExpiration_time(long expiration_time) {
		this.expiration_time = expiration_time;
	}

	public int getGet_ad_in_same_view_interval() {
		return get_ad_in_same_view_interval;
	}

	public void setGet_ad_in_same_view_interval(int get_ad_in_same_view_interval) {
		this.get_ad_in_same_view_interval = get_ad_in_same_view_interval;
	}

	public List<SdkResponseAdVO> getAdms() {
		return adms;
	}

	public void setAdms(List<SdkResponseAdVO> adms) {
		this.adms = adms;
	}

    public int getDwlconfirm() {
        return dwlconfirm;
    }

    public void setDwlconfirm(int dwlconfirm) {
        this.dwlconfirm = dwlconfirm;
    }

        public int getLg_swh() {
        return lg_swh;
    }

    public void setLg_swh(int lg_swh) {
        this.lg_swh = lg_swh;
    }

    public int getBrkdwn() {
        return brkdwn;
    }

    public void setBrkdwn(int brkdwn) {
        this.brkdwn = brkdwn;
    }

	public int getGet_ad_in_same_view_times() {
		return get_ad_in_same_view_times;
	}

	public void setGet_ad_in_same_view_times(int get_ad_in_same_view_times) {
		this.get_ad_in_same_view_times = get_ad_in_same_view_times;
	}

	public int getSilent_install() {
		return silent_install;
	}

	public void setSilent_install(int silent_install) {
		this.silent_install = silent_install;
	}

	public int getNo_ad_days() {
		return no_ad_days;
	}

	public void setNo_ad_days(int no_ad_days) {
		this.no_ad_days = no_ad_days;
	}

	public int getShow_ad_times_one_day() {
		return show_ad_times_one_day;
	}

	public void setShow_ad_times_one_day(int show_ad_times_one_day) {
		this.show_ad_times_one_day = show_ad_times_one_day;
	}

	public int getShow_ad_interval() {
		return show_ad_interval;
	}

	public void setShow_ad_interval(int show_ad_interval) {
		this.show_ad_interval = show_ad_interval;
	}

}
