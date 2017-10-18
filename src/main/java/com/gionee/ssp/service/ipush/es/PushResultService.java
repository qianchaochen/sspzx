package com.gionee.ssp.service.ipush.es;

import java.util.List;

import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;

/**
 *
 * 结果集服务，对结果集进行排序，筛选出最佳结果
 *
 */
public interface PushResultService {

    /**
     * 通过排序得到最佳结果列表
     * 
     * @param campaignVOList 传入的结果集
     * @param queryVO 查询条件
     * @return 返回最佳结果
     */
    public List<CampaignVO> getList(final List<CampaignVO> campaignVOList, final QueryVO queryVO);
}
