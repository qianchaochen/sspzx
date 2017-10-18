package com.gionee.ssp.service.adx.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.adx.AdxListService;
import com.gionee.ssp.vo.PercentItemVo;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.PercentVO;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
@Service
public class AdxListServiceImpl extends BaseAdxServiceImpl implements AdxListService{
	
	
	/**
	 * adx参数缓存
	 */
	@Resource(name="adx_map")
	public Map<String, AdxInfoVO> adx_map;
	
	@Override
	public List<AdxInfoVO> getAdxList(FillingDataVO vo)
			throws Exception {
		PercentVO percentVO=vo.getPercent();

		ArrayList<AdxInfoVO> adx_list=new ArrayList<AdxInfoVO>();
		
		if(null == percentVO){
			AdxInfoVO adxInfoVO=adx_map.get(CommonConstant.SYS_CODE.FIRM_NAME.getValue()); //返回默认
			adxInfoVO.setAdxName(CommonConstant.SYS_CODE.FIRM_NAME.getValue());
			adx_list.add(adxInfoVO);
			return adx_list;
		}
		
		if(percentVO.getFirm()>0){
			AdxInfoVO adxInfoVO=adx_map.get(CommonConstant.SYS_CODE.FIRM_NAME.getValue());
			adxInfoVO.setAdxName(CommonConstant.SYS_CODE.FIRM_NAME.getValue());
			adx_list.add(adxInfoVO);
		}
		if(percentVO.getWk()>0){
			AdxInfoVO adxInfoVO=adx_map.get(CommonConstant.SYS_CODE.WK_NAME.getValue());
			adxInfoVO.setAdxName(CommonConstant.SYS_CODE.WK_NAME.getValue());
			adx_list.add(adxInfoVO);
		}
		if(percentVO.getBaidu()>0){
			adx_list.add(new AdxInfoVO(CommonConstant.SYS_CODE.BAIDU_NAME.getValue(),baidu_req_url));
		}
		if(percentVO.getJrtt()>0){
			adx_list.add(new AdxInfoVO(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue(),toutiao_req_url));
		}
		if(percentVO.getZaker()>0){
			adx_list.add(new AdxInfoVO(CommonConstant.SYS_CODE.ZAKER_NAME.getValue(),zaker_req_url));
		}
		if(percentVO.getInmobi()>0){
			adx_list.add(new AdxInfoVO(CommonConstant.SYS_CODE.INMOBI_NAME.getValue(),inmobi_req_url));
		}
		if(percentVO.getLj()>0){
			adx_list.add(new AdxInfoVO(CommonConstant.SYS_CODE.LINGJI_NAME.getValue(),lingji_req_url));
		}

		return adx_list;
	}
	/**初始化数据
	 * 
	 */
	@Override
	public ArrayList<PercentItemVo> getPercentList(PercentVO percentVO){
		
		ArrayList<PercentItemVo> list=new ArrayList<PercentItemVo>();
		
		if(percentVO == null){
			return list;
		}
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.FIRM_NAME.getValue(),new Integer((int)(percentVO.getFirm()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.WK_NAME.getValue(),new Integer((int)(percentVO.getWk()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.BAIDU_NAME.getValue(),new Integer((int)(percentVO.getBaidu()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue(),new Integer((int)(percentVO.getJrtt()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.ZAKER_NAME.getValue(),new Integer((int)(percentVO.getZaker()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.INMOBI_NAME.getValue(),new Integer((int)(percentVO.getInmobi()* Constant.PRECISION))));
		list.add(new PercentItemVo(CommonConstant.SYS_CODE.LINGJI_NAME.getValue(),new Integer((int)(percentVO.getLj()* Constant.PRECISION))));
		
		return list;
	}
	
	/**根据adx_name，找到对应的流量百分比
	 * @param adx_name
	 * @param percentVO
	 * @return
	 */
	public double getPercent(String adx_name,PercentVO percentVO){
		if(null==percentVO){
			return 0.0;
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.FIRM_NAME.getValue())){
			return percentVO.getFirm();
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.WK_NAME.getValue())){
			return percentVO.getWk();
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.TOUTIAO_NAME.getValue())){
			return percentVO.getJrtt();
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.INMOBI_NAME.getValue())){
			return percentVO.getInmobi();
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.BAIDU_NAME.getValue())){
			return percentVO.getBaidu();
		}
		if(adx_name.equals(CommonConstant.SYS_CODE.ZAKER_NAME.getValue())){
			return percentVO.getZaker();
		}
		
		return 0.0;
	}
}
