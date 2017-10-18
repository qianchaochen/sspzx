package com.gionee.ssp.service.adx;

import java.util.ArrayList;
import java.util.List;

import com.gionee.ssp.vo.PercentItemVo;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.PercentVO;

/**根据redis配置，获取需要发送的Adx列表
 * @author dingyw
 *
 * 2017年9月8日
 */
public interface AdxListService {
	
	/**获取需要发送的Adx列表
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdxInfoVO> getAdxList(FillingDataVO vo)
			throws Exception;
	
	/**将vo的各流量配置变成一个list,
	 * @param percentVO
	 * @return
	 */
	public ArrayList<PercentItemVo> getPercentList(PercentVO percentVO);
	
	/**根据adx名字，找到流量百分比
	 * @param adx_name
	 * @param percentVO
	 * @return
	 */
	public double getPercent(String adx_name,PercentVO percentVO);

}
