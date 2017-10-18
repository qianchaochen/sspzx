package com.gionee.ssp.service.req.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.ConvertReqUserService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillUserVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqUserServiceImpl implements ConvertReqUserService{
	
	/**
	 * @title: setUser
	 * @description: 转换用户信息
	 * @param reqBuilder
	 *            sdk请求信息
	 * @param userVO
	 *            用户填充信息
	 */
	public void setUser(Builder reqBuilder, FillUserVO userVO) {
		if (userVO != null) {
			WKSSP.User.Builder userBuilder = WKSSP.User.newBuilder();
			// 转换性别
			if (StringUtils.isNotBlank(userVO.getSex())) {
				userBuilder.setGender(userVO.getSex());
			}
			List<String> ageGroups = userVO.getAge();
			// 转换用户年龄群体
			if (ageGroups != null && ageGroups.size() > 0) {
				String ageGroup;
				Iterator<String> it_ageGroup = ageGroups.iterator();
				while (it_ageGroup.hasNext()) {
					ageGroup = it_ageGroup.next();
					userBuilder.addAgeGroup(ageGroup);
				}
			}
			reqBuilder.setUser(userBuilder);
		}
	}

}
