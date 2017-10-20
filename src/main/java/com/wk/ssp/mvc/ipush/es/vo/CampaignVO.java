package com.wk.ssp.mvc.ipush.es.vo;

/**
 *
 * ES的活动对象
 *
 */
public class CampaignVO {

    private int campaign_id; // 活动id
    private int status; // 活动状态
    private long start_time; // 活动开始日期
    private long end_time; // 活动结束日期
    private int campaign_target; // 活动目标。0：无动作，1：网站打开，2：下载
    private String imptracker; // 广告主展示监播
    private String clktracker; // 广告主点击监播
    private int ad_type; // 广告形式：1、横幅, 2：插屏, 3: 开屏, 4:原生
    private String landing_url; // 落地页URL或下载链接
    private String bundle; // 下载类活动APP包名
    private int priority; // 优先级
    private int ipush_limit; // 直投限制。0:不限制,1:展示限制，2:点击限制
    private int[] advertising_times; // 投放时间。星期前三个字母+小时数
    private int[] areas; // 投放区域。地区码数组。不限为000
    private int connection_type; // 连接类型：0：全部，1:2G/3G/4G，2：WIFI
    private int[] carriers; // 运营商类型数组0：未知运营商，1：中国移动，2：中国电信，3：中国联通，4：其他运营商
    private int os; // 操作系统类型
    private OSVVO osv; // Android版本号
    private String[] models; // 机型
    private CreativeVO[] creatives; // 创意
    private int item_type; // 广告类型，1：信息流组图，2：信息流小图 3：信息流大图,4:图片,5:图文
    private int mode;  //直投交易模式
    
    private int clktracker_from;	//直投广告来源, 默认是金立的广告:0, 阿里的广告:1

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getCampaign_target() {
        return campaign_target;
    }

    public void setCampaign_target(int campaign_target) {
        this.campaign_target = campaign_target;
    }

    public String getImptracker() {
        return imptracker;
    }

    public void setImptracker(String imptracker) {
        this.imptracker = imptracker;
    }

    public String getClktracker() {
        return clktracker;
    }

    public void setClktracker(String clktracker) {
        this.clktracker = clktracker;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public String getLanding_url() {
        return landing_url;
    }

    public void setLanding_url(String landing_url) {
        this.landing_url = landing_url;
    }

    /**
     * @return the bundle
     */
    public String getBundle() {
        return bundle;
    }

    /**
     * @param bundle the bundle to set
     */
    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the ipush_limit
     */
    public int getIpush_limit() {
        return ipush_limit;
    }

    /**
     * @param ipush_limit the ipush_limit to set
     */
    public void setIpush_limit(int ipush_limit) {
        this.ipush_limit = ipush_limit;
    }

    /**
     * @param carriers the carriers to set
     */
    public void setCarriers(int[] carriers) {
        this.carriers = carriers;
    }

    public int[] getAdvertising_times() {
        return advertising_times;
    }

    public void setAdvertising_times(int[] advertising_times) {
        this.advertising_times = advertising_times;
    }

    public int[] getAreas() {
        return areas;
    }

    public void setAreas(int[] areas) {
        this.areas = areas;
    }

    public int getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(int connection_type) {
        this.connection_type = connection_type;
    }

    public int[] getCarriers() {
        return carriers;
    }

    public void setCarrier(int[] carriers) {
        this.carriers = carriers;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public OSVVO getOsv() {
        return osv;
    }

    public void setOsv(OSVVO osv) {
        this.osv = osv;
    }

    public String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }

    public CreativeVO[] getCreatives() {
        return creatives;
    }

    public void setCreatives(CreativeVO[] creatives) {
        this.creatives = creatives;
    }

    /**
     * @return the native_type
     */
    public int getItem_type() {
        return item_type;
    }

    /**
     * @param native_type the native_type to set
     */
    public void setItem_type(int native_type) {
        this.item_type = native_type;
    }

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getClktracker_from() {
		return clktracker_from;
	}

	public void setClktracker_from(int clktracker_from) {
		this.clktracker_from = clktracker_from;
	}

}
