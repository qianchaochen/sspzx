package com.gionee.ssp.service.adFlow;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年4月22日
 */
public interface AdFlowModeFilterService {
	
	
	/**总入口
	 * @param fillingDataVO
	 * @param requestVO
	 * @return
	 */
	public boolean doFilter(FillingDataVO fillingDataVO,SdkRequestVO requestVO);
	
	/**根据包名进行过滤
	 * @return
	 */
	public boolean doFilterPackage(FillingDataVO fillingDataVO,SdkRequestVO requestVO);
	
	/**根据app_id进行过滤
	 * @return
	 */
	public boolean doFilterAppId(FillingDataVO fillingDataVO,SdkRequestVO requestVO);
	
	/**根据app_id进行过滤
	 * @return
	 */
	public boolean doFilterAdSlotId(FillingDataVO fillingDataVO,SdkRequestVO requestVO);

}
