package com.gionee.ssp.service.zaker.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.exception.baidu.BaiduApiException;
import com.gionee.ssp.service.adx.impl.BaseAdxDataServiceImpl;
import com.gionee.ssp.service.zaker.ZakerDataService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.MD5Utils;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.sdk.SdkNativeVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description zaker信息层
 * @author wuxing
 * @date 2017年4月5日
 *
 */
@Service
public class ZakerDataServiceImpl extends BaseAdxDataServiceImpl implements ZakerDataService {
	
	@Override
	public HttpGet reqInfoBuilder(SdkRequestVO vo, String adxAdslotId) throws Exception  {
		String net = "wifi";
		StringBuilder url = new StringBuilder(zaker_req_url);
		
		url.append("f=")
		.append(adxAdslotId)	//广告位
		.append("&type=json")
		.append("&_udid=")
		.append(vo.getDevice().getImei_md5())
		.append("&ip=")
		.append(vo.getNetwork().getIp());
		if("1".equals(lock_debug)){
			url.append("&debug=1");
		}
		
		if(vo.getNetwork().getConnect_type()!=2){
			net = "mobile";
		}
		url.append("&_net=").append(net);//.append(Configer.getZakerDebug());
		HttpGet httpGet = new HttpGet(url.toString());
		String header= MD5Utils.md5AsString(url.toString());
		httpGet.addHeader("", header);
		//生成BidRequest对象
		return httpGet;
	}

	@Override
	public SdkResponseVO rspInfoHandler(JSONObject mobadsResponse,
			SdkRequestVO req) throws BaiduApiException,Exception {

		SdkResponseVO rsp = new SdkResponseVO();
		if(mobadsResponse.get("objects").toString().equals("[]")){
			rsp.setError_code(Errors.NO_CONTENT);
        	rsp.setAdms(new ArrayList<>());
        	return rsp;
		}
		JSONObject json = new JSONObject(mobadsResponse.get("objects").toString());		
		List<SdkResponseAdVO> sdkRspAds = new ArrayList<SdkResponseAdVO>();
		SdkResponseAdVO sdkResAdVO;
		List<String> cmurl=new ArrayList<String>();
		String read_url=json.getString("read_url");
		List<String> vmurl=new ArrayList<String>();
	    vmurl.add(read_url);  	
		sdkResAdVO = new SdkResponseAdVO();
		sdkResAdVO.setAdslot_id(req.getAdslot().getAdslot_id());
		// 现在zaker只有锁屏, 锁屏的广告类型为原生, 创意类型为信息流广告
		sdkResAdVO.setAd_cp(AdxType.ZAKER.getAdx());	//zaker
		sdkResAdVO.setAd_type(CommonConstant.AD_TYPE.NATIVE.getValue());
		sdkResAdVO.setCreative_type(CommonConstant.CreativeType.NATIVE.getValue());
		//组装原生字段
		SdkNativeVO nativeVo = assembleNative(json);
		if(nativeVo == null){
			return null;
		}
		sdkResAdVO.setNativ(nativeVo);
		sdkResAdVO.setInteraction_type(CommonConstant.SDKInteractionType.WEB.getValue());//不确定
		sdkResAdVO.setImgurl(json.getString("pic")); //物料 URL
		
		sdkResAdVO.setW(720); //宽（非必输）
		sdkResAdVO.setH(1280); //高（非必输）
		sdkResAdVO.setClkurl(json.getString("click_url")); //点击跳转地址
		
		//监播行为
		sdkResAdVO.setImptrackers(vmurl); //曝光地址,必须是ArrayList
		sdkResAdVO.setClktrackers(cmurl); //点击监播地址
//			sdkResAdVO.setDwnlst(); //下载开始监播
		
		sdkRspAds.add(sdkResAdVO);
		rsp.setRequest_id(json.getString("id"));
		rsp.setAdms(sdkRspAds);
		rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL); //自动请求广告的时间间隔
		
		return rsp;
	}
	
	/**
	 * @author: wuxing
	 * @date: 2017年4月18日 上午11:13:45
	 *
	 */
	private SdkNativeVO assembleNative(JSONObject ad){
		/**
		 * zaker返回素材样例
		 * {stat: 1,
			objects:
				[{show_type: 3,
				start_time: 1443518869,
				end_time: 1443519169,
				id: "55d6f9697f780beb3b000057",
				title: "广告标题",
				second_title: "",
				pic: "",
				short_pic: "",
				click_url: “http://ggs.myzaker.com/zk_ggs_click.php?ads_id=55d6f9697f780beb3b000057",
				read_url: "http://ggs.myzaker.com/zk_ggs_show.php?ads_id=55d6f9697f780beb3b000057",
				tag_pic:
				{
					image_url: “http://zkres.myzaker.com/data/image/mark2/ad_2x.png?v=2015061216",
					image_height: 26,
					image_width: 46
				}}]}
		 *
		 */
		//组装原生字段
		SdkNativeVO nativeVo = new SdkNativeVO();
		List<String> imgUrls = new ArrayList<String>();
		imgUrls.add(ad.getString("pic"));
		nativeVo.setImgurl(imgUrls);
		nativeVo.setTitle(ad.getString("title"));
		nativeVo.setType(2);	//大图
		//判断处理md5的情况
		if(!handleMd5(ad.getString("pic"), nativeVo)){
			return null;
		}
		return nativeVo;
	}
}
