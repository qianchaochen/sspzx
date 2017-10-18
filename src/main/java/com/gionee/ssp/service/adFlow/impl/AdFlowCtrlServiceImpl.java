package com.gionee.ssp.service.adFlow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adFlow.AdFlowCtrlService;
import com.gionee.ssp.service.adx.GetAdxNameService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 *  * 通过读取配置的ADX的优先级，如果某种优先级的
 *  1.	给每个ADX配置优先级（服务器配置）与切量比例（管理平台配置）。
	2.	当客户端发起一次广告请求时，SSP同时向所有ADX请求广告，并根据反馈的结果，按优先级选择广告资源。
	3.	优先满足优先级较高的ADX，并且不限制流量。
	4.	优先级相同的ADX之间，按切量比例分配流量。
	5.	不同优先级的ADX之间，流量分配不受切量比例的影响。
 * @author dingyw
 *
 * 2017年4月21日
 */
@Service
public class AdFlowCtrlServiceImpl extends BaseAdFlowServiceImpl implements AdFlowCtrlService{
	/**
	 * adx名字服务层
	 */
	@Autowired
	GetAdxNameService getAdxNameService;

	@Override
	public SdkResponseVO getAd(Map<String, SdkResponseVO> map,FillingDataVO fillingDataVO) throws Exception {
        
        //遍历resultMap,把没有广告的resultMap剔除，然后找到有广告内容的优先级最高的一个或多个
        Map<String, SdkResponseVO> resultMap=this.getHighLevelAd(map);
              
        //如果resultMap为空，返回空广告
        if(0 == resultMap.size()){
        	return this.getDefaultResult();
        }
        
        //如果有一个，则返回
        if(1 == resultMap.size()){
        	return this.getOneAd(resultMap);
        }
        //如果resultMap有多个，则根据流量配比，按概率选择
		return this.getFlowCtrlAd(resultMap,fillingDataVO);
	}
	
	/**默认返回没有广告内容
	 * @return
	 */
	public SdkResponseVO getDefaultResult(){
		SdkResponseVO responseVO=new SdkResponseVO();
		//创建无内容返回
		responseVO.setError_code(Errors.NO_CONTENT);
		
		//添加空返回list，防止记录返回广告个数是出现空指针
		responseVO.setAdms(new ArrayList<>());
		
		return responseVO;
	}
	/**map只有一个广告，然后返回只有一个广告
	 * @param map
	 * @return
	 */
	public SdkResponseVO getOneAd(Map<String, SdkResponseVO> map){
		 Iterator<Entry<String, SdkResponseVO>> iter = map.entrySet().iterator();
		 while (iter.hasNext()){
			 Map.Entry<String, SdkResponseVO> entry = iter.next();	 
			 this.printAdxLog(entry.getKey());
			 return entry.getValue();
		 }
		 return this.getDefaultResult();
	}
	public void printAdxLog(String adx_name){
		 if(CommonConstant.SYS_CODE.WK_NAME.getValue().equals(adx_name)){
		     WKLogManager.getLOG().addReqAdLog(Constant.TARGET_ADX, String.valueOf(AdxType.WANKA.getAdx()));
		 }
		 else if(CommonConstant.SYS_CODE.FIRM_NAME.getValue().equals(adx_name)){
			 WKLogManager.getLOG().addReqAdLog(Constant.TARGET_ADX, String.valueOf(AdxType.GIONEE.getAdx()));
		 }else{
			 WKLogManager.getLOG().addReqAdLog(Constant.TARGET_ADX, this.getAdxCode(adx_name));
		 }
	}
	/**根据adx_name打印adx_code日志
	 * @param adx_name
	 */
	private String getAdxCode(String adx_name){
		if(CommonConstant.SYS_CODE.BAIDU_NAME.getValue().equals(adx_name)){
			return String.valueOf(AdxType.BAIDU.getAdx());
		}else if(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue().equals(adx_name)){
			return String.valueOf(AdxType.TOUTIAO.getAdx());
		}else if(CommonConstant.SYS_CODE.INMOBI_NAME.getValue().equals(adx_name)){
			return String.valueOf(AdxType.INMOBI.getAdx());
		}else if(CommonConstant.SYS_CODE.ZAKER_NAME.getValue().equals(adx_name)){
			return String.valueOf(AdxType.ZAKER.getAdx());
		}else if(CommonConstant.SYS_CODE.LINGJI_NAME.getValue().equals(adx_name)){
			return String.valueOf(AdxType.LINGJI.getAdx());
		}else{
			return "";
		}
	}
	/**如果map中有多个广告，则按照流量分配，用概率去选择其中一个广告
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public SdkResponseVO getFlowCtrlAd(Map<String, SdkResponseVO> map,FillingDataVO fillingDataVO) throws Exception{
		
		if(map.size() < 2){
			return this.getDefaultResult();
		}
		//根据概率选择目标adx_name
		String target_adx_name =getAdxNameService.getAdxName(fillingDataVO,map);
		
		if(null==target_adx_name){
			return this.getDefaultResult();
		}
		 Iterator<Entry<String, SdkResponseVO>> iter = map.entrySet().iterator();
		 while (iter.hasNext()){
			 Map.Entry<String, SdkResponseVO> entry = iter.next();
			 String adx_name=entry.getKey();
			 if(adx_name.equals(target_adx_name)){
				 this.printAdxLog(entry.getKey());
				 return entry.getValue(); 
			 }
			
		 }
		 return this.getDefaultResult();
	}
	/**查找优先级最高（level的数字值最低的一个或多个）
	 * @param map
	 * @return
	 */
	public Map<String, SdkResponseVO> getHighLevelAd(Map<String, SdkResponseVO> map){
		
		  Map<String, SdkResponseVO> resultMap=new HashMap<String, SdkResponseVO>();
	      Iterator<Entry<String, SdkResponseVO>> iter = map.entrySet().iterator();
	      
	      int smallest_level=99;
	        while (iter.hasNext()) {
	        	Map.Entry<String, SdkResponseVO> entry = iter.next();
	        	String adx_name = entry.getKey();
	        	SdkResponseVO sdkResponseVO = entry.getValue();
	        	if(null==sdkResponseVO ){
	        		continue;
	        	}
	        	if(Errors.NO_CONTENT==sdkResponseVO.getError_code()){
	        		continue;
	        	}
	        	int curr_level=this.getAdxFlowLevel(adx_name);
	        	
	        	if(curr_level == smallest_level){
	        		resultMap.put(adx_name, sdkResponseVO);
	        		continue;
	        	}
	        	if(curr_level < smallest_level){ //如果发现有小的
	        		resultMap.clear(); //清空之前的列表
	        		resultMap.put(adx_name, sdkResponseVO);
	        		smallest_level=curr_level;
	        	}
	       }
	        return resultMap;
	        
	}

	public int getAdxFlowLevel(String adx_name){
		if(adx_name.equals(CommonConstant.SYS_CODE.FIRM_NAME.getValue())){
			return FIRM_FLOW_LEVEL; 
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.WK_NAME.getValue())){
			return WK_FLOW_LEVEL;
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.BAIDU_NAME.getValue())){
			return BAIDU_FLOW_LEVEL;
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue())){
			return TOUTIAO_FLOW_LEVEL;
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.INMOBI_NAME.getValue())){
			return INMOBI_FLOW_LEVEL;
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.ZAKER_NAME.getValue())){
			return ZAKER_FLOW_LEVEL;
		}
		return 100; //默认
	}
}
