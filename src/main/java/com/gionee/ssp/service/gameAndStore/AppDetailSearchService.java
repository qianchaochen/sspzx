package com.gionee.ssp.service.gameAndStore;

import java.util.List;

import com.gionee.ssp.vo.gameAndStore.AppDetailQueryVo;
import com.wk.ssp.vo.sdk.AppDetailInfoVo;

/**
 * 
 * @ClassName AppDetailSearchService
 * @Desc {从es中查询应用商店和游戏大厅应用详情}
 * @author zhengk
 * @date Mar 13, 2017
 */
public interface AppDetailSearchService {

	/**从es中查询应用商店和游戏大厅应用详情
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	List<AppDetailInfoVo> search(AppDetailQueryVo queryVO) throws Exception;

}
