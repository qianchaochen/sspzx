package com.gionee.ssp.service.lingji.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adx.impl.BaseAdxDataServiceImpl;
import com.gionee.ssp.service.lingji.LingjiDataService;
import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

@Service
public class LingjiDataServiceImpl extends BaseAdxDataServiceImpl implements LingjiDataService{
	
	@Override
	public HttpGet reqInfoBuilder(SdkRequestVO req, FillingDataVO vo, AdxAdslotInfoVo adx) throws Exception {
		StringBuilder url = new StringBuilder(lingji_req_url);
		//灵集广告位ID 178262
	 	url.append("l=").append(adx.getAdslotId())
	 	.append("&r=1")
	 	.append("&v=0")
	 	.append("&bt=0")
	 	.append("&m_os=").append("Android")
	 	.append("&m_osv=").append(req.getDevice().getOs_version())
	 	.append("&m1=").append(req.getDevice().getAndroid_id())
	 	.append("&m2=").append(req.getDevice().getImei_md5())
	 	.append("&m3=").append(req.getDevice().getImei_md5())
	    .append("&m_app_pn=").append(adx.getAppPackage())
	    .append("&m_app").append(adx.getAppPackage())
	    .append("&m_adw=").append(vo.getNativ().get(0).getW())
	    .append("&m_adh=").append(vo.getNativ().get(0).getH())
	    .append("&m_ts=").append(req.getGps().getTimestamp())
	    .append("&m_ip=").append(req.getNetwork().getIp())
	    .append("&m_net=").append(req.getNetwork().getConnect_type());
		
		HttpGet httpGet = new HttpGet(url.toString());
		return httpGet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse,
			SdkRequestVO requestVO) throws Exception {
		// 处理灵集的response
		SdkResponseVO sdkResponseVO = new SdkResponseVO();
		List<SdkResponseAdVO> sdkRspAds = new ArrayList<SdkResponseAdVO>();
		SdkResponseAdVO sdkResAdVO;
		// 处理展示监播地址
		List<String> vmurl = new ArrayList<String>();
		JSONObject pm = mobadsResponse.getJSONObject("pm");
		if(pm.has("0")){
			JSONArray imptracker = pm.getJSONArray("0");
			vmurl = (List<String>)(List)imptracker.toList();
		}
		// 处理点击监播地址
		JSONArray clktracker = mobadsResponse.getJSONArray("cm");
		
		List<String> cmurl = (List<String>)(List)clktracker.toList();
		
		sdkResAdVO = new SdkResponseAdVO();
		sdkResAdVO.setAdslot_id(requestVO.getAdslot().getAdslot_id());
		//TODO 现在灵集只有锁屏, 锁屏的广告类型为原生, 创意类型为信息流广告
		sdkResAdVO.setAd_cp(AdxType.LINGJI.getAdx());	//灵集
		sdkResAdVO.setAd_type(4);
		sdkResAdVO.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
		//组装原生字段
		SdkNativeVO nativeVo = assembleNative(mobadsResponse);
		if(nativeVo == null){
			return null;
		}
		sdkResAdVO.setNativ(nativeVo);
		sdkResAdVO.setInteraction_type(CommonConstant.SDKInteractionType.WEB.getValue());//不确定
		sdkResAdVO.setImgurl(mobadsResponse.getString("src")); //物料 URL
		
		sdkResAdVO.setW(mobadsResponse.getInt("adw")); //宽（非必输）
		sdkResAdVO.setH(mobadsResponse.getInt("adh")); //高（非必输）
		sdkResAdVO.setClkurl(mobadsResponse.getString("deeplink_url")); //点击跳转地址
		
		//监播行为
		sdkResAdVO.setImptrackers(vmurl); //曝光地址,必须是ArrayList
		sdkResAdVO.setClktrackers(cmurl); //点击监播地址
//			sdkResAdVO.setDwnlst(); //下载开始监播
		
		sdkRspAds.add(sdkResAdVO);
		sdkResponseVO.setRequest_id(mobadsResponse.getString("id"));
		sdkResponseVO.setAdms(sdkRspAds);
		sdkResponseVO.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL); //自动请求广告的时间间隔
		
		return sdkResponseVO;
	}
	
	/**
	 * @author: wuxing
	 * @date: 2017年4月18日 上午11:13:45
	 *
	 */
	private SdkNativeVO assembleNative(JSONObject ad){
		/**
		 * 灵集返回素材样例
		 * {
		    "pid": "100",
		    "etype": "V",
		    "type": "V",
		    "src": "http://s.stfile.com/8b/e8b856fcbe31dfa3a41ed03ba8518acb.flv",
		    "adw": 450,
		    "adh": 300,
		    "pm": {"7":["http://g.cn.xtgreat.com/x.gif?783&bc=90_14030_1355798855_1&freq=1&k=1003043&p=3xxL60&rt=2&o="], "15":["http://xxx.com/...", "http://yyy.com/..."]},
		    "cm": ["http://www.test1.com","http://www.test2.com"],
		    "ldp": "http://e.cn.xtgreat.com/r.gif?&bc=90_14030_1355798855_1&k=1003043&p=3xxL60&ro=sm&rt=2&o=http://www.braun.com/cn/home.html",
		    "meta": {"check":1,duration":15}
		    }
		 *
		 */
		//组装原生字段
		SdkNativeVO nativeVo = new SdkNativeVO();
		
		List<String> imgUrls = new ArrayList<String>();
		imgUrls.add(ad.getString("src"));
		nativeVo.setImgurl(imgUrls);
		nativeVo.setTitle(ad.has("title") ? ad.getString("title") : "广告标题");
		nativeVo.setType(2);	//大图
		//判断处理md5的情况
		if(!handleMd5(ad.getString("src"), nativeVo)){
			return null;
		}
		return nativeVo;
	}
}
