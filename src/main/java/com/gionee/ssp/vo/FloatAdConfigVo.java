package com.gionee.ssp.vo;

import com.gionee.common.vo.BaseVo;

import java.util.List;

/**
 * 浮标广告属性配置
 * @author: qiancc
 * 2017年10月19日
 */
public class FloatAdConfigVo extends BaseVo {

    /**浮标广告可配置该广告位触发后，延迟多久请求广告。精确到秒。默认不延迟。*/
    private int delayShow;

    /**定时关闭，默认为0：不关闭；可配置该广告位展示广告多久之后自动消失。精确到秒。*/
    private int setTimeOut;

    /**屏蔽广告投放的H5域名列表*/
    private List<String> blackList;

    public int getDelayShow() {
        return delayShow;
    }

    public void setDelayShow(int delayShow) {
        this.delayShow = delayShow;
    }

    public int getSetTimeOut() {
        return setTimeOut;
    }

    public void setSetTimeOut(int setTimeOut) {
        this.setTimeOut = setTimeOut;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }
}
