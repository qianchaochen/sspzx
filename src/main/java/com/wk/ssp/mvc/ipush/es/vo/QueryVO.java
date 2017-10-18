package com.wk.ssp.mvc.ipush.es.vo;

import java.util.ArrayList;
import java.util.List;

import com.gionee.common.vo.BaseVo;


/**查询条件对象
 * @author dingyw
 *
 * 2017年10月16日
 */
public class QueryVO extends BaseVo{

	private int count; // 直投的广告条数
	private int adslot; // 广告位id
	private int ad_type; // 创意形式。1-横幅 2-插屏
	private int area; // 投放区域
	private int connectionType; // 连接类型
	private int carrier;// 运营商
	private String os; // 操作系统类型
	private String osv; // 系统版本
	private String model; // make + model
	private List<CreativeMessageVO> CreativeMessageVO; // 创意信息
	private List<Integer> bitc; // 屏蔽动作类型

	private String ip; // ip地址
	private String imeiMd5; // imei的MD5值
	private String bidRequestId; // 竞价请求的id
	private String dpidMd5; // android 设备id

	// v1.0.4增加人群定向
	private List<String> interests; // DMP用户标签
	private String gender; // 0 不限 1 男 2 女
	private String age; // 年龄段
	private List<String> apps; // 年龄段

	public QueryVO() {
		super();
		apps = new ArrayList<>();
		interests = new ArrayList<>();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAdslot() {
		return adslot;
	}

	public void setAdslot(int adslot) {
		this.adslot = adslot;
	}

	public int getAd_type() {
		return ad_type;
	}

	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}

	public int getCarrier() {
		return carrier;
	}

	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<CreativeMessageVO> getCreativeMessageVO() {
		return CreativeMessageVO;
	}

	public void setCreativeMessageVO(List<CreativeMessageVO> creativeMessageVO) {
		CreativeMessageVO = creativeMessageVO;
	}

	public List<Integer> getBitc() {
		return bitc;
	}

	public void setBitc(List<Integer> bitc) {
		this.bitc = bitc;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getImeiMd5() {
		return imeiMd5;
	}

	public void setImeiMd5(String imeiMd5) {
		this.imeiMd5 = imeiMd5;
	}

	public String getBidRequestId() {
		return bidRequestId;
	}

	public void setBidRequestId(String bidRequestId) {
		this.bidRequestId = bidRequestId;
	}

	public String getDpidMd5() {
		return dpidMd5;
	}

	public void setDpidMd5(String dpidMd5) {
		this.dpidMd5 = dpidMd5;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public void addInterests(String interest) {
		this.interests.add(interest);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<String> getApps() {
		return apps;
	}

	public void setApps(List<String> apps) {
		this.apps = apps;
	}

	public void addApps(String app) {
		this.apps.add(app);
	}

}
