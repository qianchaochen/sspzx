package com.wk.ssp.vo;

import com.gionee.common.vo.BaseVo;

import java.util.List;

/**
 * 浮标广告
 * @author: qiancc
 * 2017年10月19日
 */
public class FillFloatVo extends BaseVo {

    /**投放域名限制 0：屏蔽列表中的域名；1：只投放列表中的域名*/
    private int domainRestrict;

    /**投放限制的域名列表*/
    private List<String> domainList;

    public int getDomainRestrict() {
        return domainRestrict;
    }

    public void setDomainRestrict(int domainRestrict) {
        this.domainRestrict = domainRestrict;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }
}
