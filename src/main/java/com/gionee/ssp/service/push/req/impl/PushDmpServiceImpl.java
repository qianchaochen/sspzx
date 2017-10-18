package com.gionee.ssp.service.push.req.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.req.PushDmpService;
import com.gionee.ssp.service.redis.dmp.DmpRedisService;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class PushDmpServiceImpl implements PushDmpService{
	
	Logger logger = LogManager.getLogger(getClass());
	/**
	 *dmp服务层
	 */
	@Autowired
	DmpRedisService dmpRedisService;
	/**
	 *兴趣lable长度
	 */
	private static int interest_length=7;

	/**
	 * app 兴趣label长度
	 */
	private static int app_length=14;
	
	public void setDmpQueryInfo(QueryVO queryVO) throws Exception{
		// 获取用户的DMP标签
		List<String> lables = new ArrayList<>();
		if (!StringUtils.isEmpty(queryVO.getImeiMd5())) {
			try{
				lables = dmpRedisService.queryList(queryVO.getImeiMd5().toLowerCase());
			}catch(Exception e){
				e.printStackTrace();	
				logger.error(e.getMessage(),e);
			}
		}

		if (CollectionUtils.isEmpty(lables)) {
			return;	
		}
		this.getDmpInfo(queryVO, lables);
	}
	/**获取dmp标签
	 * @param queryVO
	 * @param lables
	 */
	private void getDmpInfo(QueryVO queryVO,List<String> lables){
		for (String lable : lables) {

			if (lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.GENDER_LABEL.getValue())) {
				queryVO.setGender(lable);
			} else if (lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.AGE_LABLE.getValue())) {
				queryVO.setAge(lable);
			} else if ((lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.HOBBY_201.getValue()) 
					|| lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.HOBBY_202.getValue()) 
					|| lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.HOBBY_203.getValue()))
					&& lable.length() == interest_length) {
				queryVO.addInterests(lable);
			} else if ((lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.HOBBY_201.getValue()) 
					|| lable.startsWith(CommonConstant.DMP_LABLE_PREFIX.HOBBY_202.getValue())) 
					&& lable.length() == app_length) {
				queryVO.addApps(lable);
			}
		}
	}
}
