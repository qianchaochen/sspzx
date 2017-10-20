package com.wk.ssp.vo;

import java.util.List;

import com.gionee.common.vo.BaseVo;
import com.gionee.ssp.vo.PlatAdConfVo;

/**
 * @description: 广告请求业务数据填充
 *
 * modified by zhengk 106增加应用分发广告的打开类型jump_type
 */
public class FillingDataVO extends BaseVo{

	/** 应用名 **/
	private String app_name;
	
	/** 应用包名 **/
	private String bundle;
	
	/** 应用类型 **/
	private List<String> cat;
	
	/** 应用关键字 **/
	private String keywords;
	
	/** 受众信息 **/
	private FillUserVO user;
	
	/** 屏蔽行业 **/
	private List<String> bcat;
	
	/** 屏蔽广告主 **/
	private List<String> badv;

	/** 竞价底价 **/
	private double bidfloor;

	/** 底价币种 **/
	private String bidfloorcur;

	/** 是否全插屏。0表示非插屏, 1表示插屏或全插屏 **/
	private int instl;

	/** 是否开屏.0:不是开屏，非0表示开屏 **/
	private int is_splash;

	/** 广告位填充信息 **/
	private FillBannerVO banner;

	/** 广告位流量分配比例 **/
	private PercentVO percent;

	/** 应用审核状态 **/
	private int status;

	/** 是否为直投广告 0:不是直投， 1：直投**/
	private int is_ipush;

	/** 直投广告状态 **/
	private String ipush_status;

	/** 直投百分比 **/
	private double ipush_percent;

	/** 原生广告数据 **/
	private List<FillNativeVO> nativ;

	/** 屏蔽创意动作类型 **/
	//最开始这个字段是屏蔽的动作类型，从103开始就已经改成接受的动作类型，  不是屏蔽，不是屏蔽，不是屏蔽
	private List<Integer> bitc;

	/** 广告请求个数 ， 默认值为1**/
	private int ad_cnt = 1;

	/**百度ID信息*/
	private PlatAdConfVo baidu;

	/**inmobi ID信息*/
	private PlatAdConfVo inmobi;

	/**zaker ID信息*/
	private PlatAdConfVo zaker;

	/**toutiao ID信息*/
	private PlatAdConfVo jrtt;

	/**竞价开关  0 开启竞价， 1关闭竞价*/
	private String bid_switch;

	//106增加功能，应用分发广告的打开方式   1：h5 落地页  2：直接下载，5：下载卡片，6：跳转游戏大厅、应用商店
	private int jump_type;

	/**用户一天内看到广告的次数*/
	private int show_ad_times_one_day;

	/**两次广告的时间间隔*/
	private int show_ad_interval;

	/**该广告位屏蔽的机型*/
	private List<String> bModels;

	/**浮标广告*/
	private FillFloatVo floatVo;

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public List<String> getCat() {
		return cat;
	}

	public void setCat(List<String> cat) {
		this.cat = cat;
	}
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public FillUserVO getUser() {
		return user;
	}

	public void setUser(FillUserVO user) {
		this.user = user;
	}

	public List<String> getBcat() {
		return bcat;
	}

	public void setBcat(List<String> bcat) {
		this.bcat = bcat;
	}

	public List<String> getBadv() {
		return badv;
	}

	public void setBadv(List<String> badv) {
		this.badv = badv;
	}

	public double getBidfloor() {
		return bidfloor;
	}

	public void setBidfloor(double bidfloor) {
		this.bidfloor = bidfloor;
	}

	public String getBidfloorcur() {
		return bidfloorcur;
	}

	public void setBidfloorcur(String bidfloorcur) {
		this.bidfloorcur = bidfloorcur;
	}

	public int getInstl() {
		return instl;
	}

	public void setInstl(int instl) {
		this.instl = instl;
	}

	public int getIs_splash() {
		return is_splash;
	}

	public void setIs_splash(int is_splash) {
		this.is_splash = is_splash;
	}

	public FillBannerVO getBanner() {
		return banner;
	}

	public void setBanner(FillBannerVO banner) {
		this.banner = banner;
	}

	public PercentVO getPercent() {
		return percent;
	}

	public void setPercent(PercentVO percent) {
		this.percent = percent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIs_ipush() {
		return is_ipush;
	}

	public void setIs_ipush(int is_ipush) {
		this.is_ipush = is_ipush;
	}

	public String getIpush_status() {
		return ipush_status;
	}

	public void setIpush_status(String ipush_status) {
		this.ipush_status = ipush_status;
	}

	public double getIpush_percent() {
		return ipush_percent;
	}

	public void setIpush_percent(double ipush_percent) {
		this.ipush_percent = ipush_percent;
	}

	public List<FillNativeVO> getNativ() {
		return nativ;
	}

	public void setNativ(List<FillNativeVO> nativ) {
		this.nativ = nativ;
	}

	public List<Integer> getBitc() {
		return bitc;
	}

	public void setBitc(List<Integer> bitc) {
		this.bitc = bitc;
	}

	public int getAd_cnt() {
		return ad_cnt;
	}

	public void setAd_cnt(int ad_cnt) {
		this.ad_cnt = ad_cnt;
	}


	public String getBid_switch() {
		return bid_switch;
	}

	public void setBid_switch(String bid_switch) {
		this.bid_switch = bid_switch;
	}

	public int getJump_type() {
		return jump_type;
	}

	public void setJump_type(int jump_type) {
		this.jump_type = jump_type;
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

	public PlatAdConfVo getBaidu() {
		return baidu;
	}

	public void setBaidu(PlatAdConfVo baidu) {
		this.baidu = baidu;
	}

	public PlatAdConfVo getInmobi() {
		return inmobi;
	}

	public void setInmobi(PlatAdConfVo inmobi) {
		this.inmobi = inmobi;
	}

	public PlatAdConfVo getZaker() {
		return zaker;
	}

	public void setZaker(PlatAdConfVo zaker) {
		this.zaker = zaker;
	}

	public PlatAdConfVo getJrtt() {
		return jrtt;
	}

	public void setJrtt(PlatAdConfVo jrtt) {
		this.jrtt = jrtt;
	}

	public List<String> getbModels() {
		return bModels;
	}

	public void setbModels(List<String> bModels) {
		this.bModels = bModels;
	}

	public FillFloatVo getFloatVo() {
		return floatVo;
	}

	public void setFloatVo(FillFloatVo floatVo) {
		this.floatVo = floatVo;
	}
}
