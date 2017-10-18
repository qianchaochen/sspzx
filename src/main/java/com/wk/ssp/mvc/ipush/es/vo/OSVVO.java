package com.wk.ssp.mvc.ipush.es.vo;

/**
 *
 * Android版本号
 *
 */
public class OSVVO {

    private int limit; // 版本限制 0：为不限，1：为只限制最低版本，2：限制版本最高和最低版本
    private int min_ver; // 最低版本，若limit为1或2时，该字段存在
    private int max_ver; // 最高版本。Limit为2时该字段存在

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getMin_ver() {
        return min_ver;
    }

    public void setMin_ver(int min_ver) {
        this.min_ver = min_ver;
    }

    public int getMax_ver() {
        return max_ver;
    }

    public void setMax_ver(int max_ver) {
        this.max_ver = max_ver;
    }

}
