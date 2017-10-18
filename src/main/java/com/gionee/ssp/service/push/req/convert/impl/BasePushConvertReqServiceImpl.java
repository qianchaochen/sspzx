package com.gionee.ssp.service.push.req.convert.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.req.convert.ConvertPushReqCreativeService;
import com.wk.model.adx.WKSSP.Device;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
public class BasePushConvertReqServiceImpl {
	
	/**
	 * 转换请求创意服务层
	 */
	@Autowired
	ConvertPushReqCreativeService convertPushReqCreativeService;
	
	protected int getConnectionType(Device device){
    	 int connectionType = CommonConstant.ConnectionType.ALL.getValue();
        if (device.getConnectType().getNumber() == CommonConstant.ConnectionType.ALL.getValue()) { // 全部
            connectionType = CommonConstant.ConnectionType.ALL.getValue();
        } else if (device.getConnectType().getNumber() == CommonConstant.ConnectionType.WIFI.getValue()) { // wifi
            connectionType = CommonConstant.ConnectionType.WIFI.getValue();
        } else { // 2G/3G/4G
            connectionType = CommonConstant.ConnectionType.CARRIER_TYPE.getValue();
        }
        return connectionType;
    }
    /**
     * 通过制造厂商和机型进行数据匹配，筛选出正确的机型
     * 
     * @param make 制造厂商
     * @param model 机型
     * @return 返回正确的机型
     */
	protected String getModelByMakeAndModel(String make, String model) {
        if (!StringUtils.isBlank(make) && !StringUtils.isBlank(model)) {
            return make.toUpperCase() + "_" + model.toLowerCase();
        } else if (!StringUtils.isBlank(make)) {
            return make.toUpperCase() + "_" + model;
        } else if (!StringUtils.isBlank(model)) {
            return make + "_" + model.toLowerCase();
        }
        return make + "_" + model;
    }

}
