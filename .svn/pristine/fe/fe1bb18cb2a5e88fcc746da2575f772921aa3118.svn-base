package com.gionee.ssp.service.adx.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adx.AdxListService;
import com.gionee.ssp.service.adx.GetAdxNameService;
import com.gionee.ssp.vo.PercentItemVo;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.PercentVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
@Service
public class GetAdxNameServiceImpl implements GetAdxNameService{
	
	/**
	 * adx列表服务层
	 */
	@Autowired
	AdxListService adxListService;
	
	/**
	 * @title: getADXName
	 * @description: 获取发送目标ADX
	 * @param percentVO
	 * @return
	 * @throws Exception
	 */
	public String getADXName(PercentVO percentVO) throws Exception{
		 
		ArrayList<PercentItemVo> list=adxListService.getPercentList(percentVO);
		 
		String adx_name = this.getAdxName(list);
		
		if(adx_name==null){
			return CommonConstant.SYS_CODE.FIRM_NAME.getValue();
		}
		return adx_name;
	}
	/**根据列表中的百分比，随机一个数，选取一个adx
	 * @param list
	 * @return
	 */
	public String getAdxName(ArrayList<PercentItemVo> list){
		 double random = Math.random(); 
		 int target = (int)(random * Constant.PRECISION);
		 
		 int sum=0;
		 for(int i=0;i<list.size();i++){
			 PercentItemVo item=list.get(i);
			 if(sum>Constant.PRECISION){ //如果数据设置错误,导致加起来概率大于1，则默认选取金立的ADX
				 return null;
			 }
			 if(sum+item.getScore()<=target){
				 sum+=item.getScore();
			 }else{
				 return item.getAdx_name();
			 }
		 }
		 return null;//默认返回
	}
	
	@Override
	public String getAdxName(FillingDataVO vo,
			Map<String, SdkResponseVO> map) throws Exception {
	
		PercentVO percentVO=vo.getPercent();
		//如果流量配比的vo为null,则默认选择第一个
		if(null == percentVO){
			return this.defaultAdxName(map);
		}
		ArrayList<PercentItemVo> list=new ArrayList<PercentItemVo>();
		//迭代map，找到对应adx的流量配比总和
		Iterator<Entry<String, SdkResponseVO>> iter = map.entrySet().iterator();
		 
		double sum=0.0;
		while (iter.hasNext()){
			 Map.Entry<String, SdkResponseVO> entry = iter.next();
			 //根据adx_name，找到对应的流量百分比
			 sum+=adxListService.getPercent(entry.getKey(), percentVO);
		 }
		if(Math.abs(sum) < 0.0001){ //如果sum==0,则返回默认
			return this.defaultAdxName(map);
		}
		//计算放大因子
		double factor= 1.0 / sum;
		
		//重新迭代
		iter = map.entrySet().iterator();
		while (iter.hasNext()){
			 Map.Entry<String, SdkResponseVO> entry = iter.next();
			 //根据adx_name，找到对应的流量百分比
			 String adx_name=entry.getKey();
			 double percent = adxListService.getPercent(adx_name, percentVO);
			 list.add(new PercentItemVo(adx_name,new Integer((int)(percent * factor * Constant.PRECISION))));
		 }
		
		return this.getAdxName(list);
	}
	/**map只有一个广告，然后返回只有一个广告
	 * @param map
	 * @return
	 */
	public String defaultAdxName(Map<String, SdkResponseVO> map){
		 Iterator<Entry<String, SdkResponseVO>> iter = map.entrySet().iterator();
		 while (iter.hasNext()){
			 Map.Entry<String, SdkResponseVO> entry = iter.next();
			 return entry.getKey();
		 }
		 return null;
	}


}
