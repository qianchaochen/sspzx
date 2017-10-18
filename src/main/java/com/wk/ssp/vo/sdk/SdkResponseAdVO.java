package com.wk.ssp.vo.sdk;

import java.util.List;

import com.gionee.common.vo.BaseVo;

/**
 * @description:sdk响应广告信息
 * // modified zhengk 2017-03-10 增加应用商店、游戏大厅专用应用详情信息字段
 * // modified zhengk 2017-09-13 增加app启动监播,h5地址，deep_link
 * // modified zhengk 2017-09-18 增加106 4g下载开始完成监播，预约开始完成监播
 */
public class SdkResponseAdVO extends BaseVo {

	/** 广告位ID **/
	private String adslot_id;
	/**
	 *广告类型。1:横幅，2: 开屏，3: 插屏，4：原生 
	 */
	private int ad_type;

	/** 创意类型 
	 * 0：无创意类型，1：纯文字创意，2：纯图片创意， 3:图文混合创意，4：H5创意。5:信息流广告
	 * **/
	private int creative_type;

	/** 交互类型 0：无动作，  1：网页打开，2：直接下载，3：使用外部浏览器打开。4：应用唤醒,5：打开下载卡片, 6跳转游戏大厅/应用商店 **/   
	private int interaction_type;

	/** 下载类广告应用包名 **/
	private String bundle;

	/** 创意图片地址 **/
	private String imgurl;

	/** 素材宽度 **/
	private int w;

	/** 素材高度 **/
	private int h;

	/** 应用详细信息- 游戏大厅&应用商店专用 **/
	private AppDetailInfoVo game_store_extra;

	/** 广告点击跳转地址 **/
	private String clkurl;
	
	/** 广告H5地址 **/
	private String h5_url;
	
	/** 广告deeplink **/
	private String deep_link;

	/** 曝光追踪地址 **/
	private List<String> imptrackers;

	/** 点击追踪地址 **/
	private List<String> clktrackers;

	/** H5创意 **/
	private SdkMixVO mix;

	/** 图文混合类创意 **/
	private SdkNativeVO nativ;

	/** 下载追踪地址 **/
	private List<String> dwnltrackers;

	/** 安装追踪地址 **/
	private List<String> intltrackers;

	/** 激活追踪地址 **/
	private List<String> actvtrackers;

	/** 下载开始监播 **/
	private List<String> dwnlst;
	
	/**  APP启动监播 **/
	private List<String> launchtrackers;
	
	/**  4G下载开始监播 **/
	private List<String> dwnlst_4g;
	/**  4G下载完成监播 **/
	private List<String> dwnltrackers_4g;
	/**  预约下载开始监播 **/
	private List<String> dwnlst_order;
	/**  预约下载完成监播 **/
	private List<String> dwnltrackers_order;
	
	/** 请求超时时间 单位秒**/
	private double rqto = 3;

	/** 渲染超时时间 单位秒 **/
	private int rdto = 2;

	/** 点击监播发送时机。1：页面开始加载时发，2：页面加载完成时发，3：点击就发，4：开始下载时发 **/
	private int ctop = 2;

	/** 广告时间 **/
	private AdTimeVO ad_time;

	/** 广告cp **/
	private int ad_cp;

	/** 广告有效期 **/
	private long ad_expire;

	public String getAdslot_id() {
		return adslot_id;
	}

	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}

	public int getCreative_type() {
		return creative_type;
	}

	public void setCreative_type(int creative_type) {
		this.creative_type = creative_type;
	}

	public int getInteraction_type() {
		return interaction_type;
	}

	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public AppDetailInfoVo getGame_store_extra() {
		return game_store_extra;
	}

	public void setGame_store_extra(AppDetailInfoVo game_store_extra) {
		this.game_store_extra = game_store_extra;
	}

	public String getClkurl() {
		return clkurl;
	}

	public void setClkurl(String clkurl) {
		this.clkurl = clkurl;
	}

	public String getH5_url() {
		return h5_url;
	}

	public void setH5_url(String h5_url) {
		this.h5_url = h5_url;
	}

	public String getDeep_link() {
		return deep_link;
	}

	public void setDeep_link(String deep_link) {
		this.deep_link = deep_link;
	}

	public List<String> getImptrackers() {
		return imptrackers;
	}

	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}

	public List<String> getClktrackers() {
		return clktrackers;
	}

	public void setClktrackers(List<String> clktrackers) {
		this.clktrackers = clktrackers;
	}

	public SdkMixVO getMix() {
		return mix;
	}

	public void setMix(SdkMixVO mix) {
		this.mix = mix;
	}

	public SdkNativeVO getNativ() {
		return nativ;
	}

	public void setNativ(SdkNativeVO nativ) {
		this.nativ = nativ;
	}

	public List<String> getDwnltrackers() {
		return dwnltrackers;
	}

	public void setDwnltrackers(List<String> dwnltrackers) {
		this.dwnltrackers = dwnltrackers;
	}

	public List<String> getIntltrackers() {
		return intltrackers;
	}

	public void setIntltrackers(List<String> intltrackers) {
		this.intltrackers = intltrackers;
	}

	public List<String> getActvtrackers() {
		return actvtrackers;
	}

	public void setActvtrackers(List<String> actvtrackers) {
		this.actvtrackers = actvtrackers;
	}

	public int getAd_type() {
		return ad_type;
	}

	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}

	public List<String> getDwnlst() {
		return dwnlst;
	}

	public void setDwnlst(List<String> dwnlst) {
		this.dwnlst = dwnlst;
	}

	public List<String> getLaunchtrackers() {
		return launchtrackers;
	}

	public void setLaunchtrackers(List<String> launchtrackers) {
		this.launchtrackers = launchtrackers;
	}
	
	public List<String> getDwnlst_4g() {
		return dwnlst_4g;
	}

	public void setDwnlst_4g(List<String> dwnlst_4g) {
		this.dwnlst_4g = dwnlst_4g;
	}

	public List<String> getDwnltrackers_4g() {
		return dwnltrackers_4g;
	}

	public void setDwnltrackers_4g(List<String> dwnltrackers_4g) {
		this.dwnltrackers_4g = dwnltrackers_4g;
	}

	public List<String> getDwnlst_order() {
		return dwnlst_order;
	}

	public void setDwnlst_order(List<String> dwnlst_order) {
		this.dwnlst_order = dwnlst_order;
	}

	public List<String> getDwnltrackers_order() {
		return dwnltrackers_order;
	}

	public void setDwnltrackers_order(List<String> dwnltrackers_order) {
		this.dwnltrackers_order = dwnltrackers_order;
	}

	public double getRqto() {
		return rqto;
	}

	public void setRqto(double rqto) {
		this.rqto = rqto;
	}

	public int getRdto() {
		return rdto;
	}

	public void setRdto(int rdto) {
		this.rdto = rdto;
	}

	public int getCtop() {
		return ctop;
	}

	public void setCtop(int ctop) {
		this.ctop = ctop;
	}

	public AdTimeVO getAd_time() {
		return ad_time;
	}

	public int getAd_cp() {
		return ad_cp;
	}

	public long getAd_expire() {
		return ad_expire;
	}

	public void setAd_time(AdTimeVO ad_time) {
		this.ad_time = ad_time;
	}

	public void setAd_cp(int ad_cp) {
		this.ad_cp = ad_cp;
	}

	public void setAd_expire(long ad_expire) {
		this.ad_expire = ad_expire;
	}
}
