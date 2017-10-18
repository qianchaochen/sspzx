package com.gionee.ssp.service.push.result.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.push.result.PushResultListService;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class PushResultListServiceImpl implements PushResultListService{
	
	   /**
     *获取排序后的列表
     * 
     * @param list 活动列表列表
     * @param count 广告条数
     * @return 返回一个创意
     */
    public List<CampaignVO> getList(List<CampaignVO> list, int count) {

        List<CampaignVO> result = new ArrayList<CampaignVO>();
        
        
        if (ObjectUtils.isEmpty(list)) {
            return result;
        }
        //CPT > CPM > CPC, 根据直投模式优先级去拿直投广告
        //打乱顺序, 获得初始的随机性
        Collections.shuffle(list);
        
        //根据mode进行排序, 分别将所有活动以cpt,cpm,cpc的顺序进行排序
        Collections.sort(list, modeComparator);
        
        //此处有bug,如果list的条数不够count，则会出错，但需求不明确，暂时不改 by dingyw 2017-10-16
        for (int i = 0; i < count; i++) {
            CampaignVO vo = list.get(i);
            result.add(vo);
        }
        return result;
    }
    
    
    /**
	 * ecpm竞价比较器
	 */
	private final Comparator<CampaignVO> modeComparator = new Comparator<CampaignVO>() {
		@Override
		public int compare(CampaignVO one, CampaignVO two) {
			if (one.getMode() - two.getMode() > 0) {
				return 1;
			} else if (one.getMode() - two.getMode() < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	};

}
