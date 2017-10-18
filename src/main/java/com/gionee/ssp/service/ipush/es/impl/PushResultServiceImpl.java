package com.gionee.ssp.service.ipush.es.impl;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.ipush.es.PushResultService;
import com.gionee.ssp.service.push.result.PushResultListService;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;


/**结果集服务实现类
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class PushResultServiceImpl implements PushResultService {
	
	/**
	 * 直投广告返回列表查询
	 */
	@Autowired
	PushResultListService pushResultListService;

    @Override
    public List<CampaignVO> getList(final List<CampaignVO> list, final QueryVO queryVO) {
        List<CampaignVO> result = null;

        if (ObjectUtils.isEmpty(list)) {
            return result;
        }

        result = pushResultListService.getList(list, queryVO.getCount());

        // 活动列表的大小
        int campaignSize = result.size();
        for (int i = 0; i < campaignSize; i++) {
            CampaignVO vo = result.get(i);
            CreativeVO creativeVO = this.randomCreativeVO(vo, queryVO);
            vo.setCreatives(new CreativeVO[] { creativeVO });
        }
        return result;
    }


    /**获取随机
     * @param campaignVO
     * @param queryVO
     * @return
     */
    private CreativeVO randomCreativeVO(final CampaignVO campaignVO, final QueryVO queryVO) {
        CreativeVO[] creativeVOs = campaignVO.getCreatives();
        if(creativeVOs.length == 0){
        	return null;
        }
        // 随机选取一个符合规格的广告进行显示
        int num = RandomUtils.nextInt(0, creativeVOs.length);
        return creativeVOs[num];
    }

}
