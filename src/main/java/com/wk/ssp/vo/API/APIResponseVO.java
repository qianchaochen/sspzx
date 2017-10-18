package com.wk.ssp.vo.API;

import java.util.List;

import com.wk.ssp.mvc.Constant;

/**
 * @description: 
 */
public class APIResponseVO {

    private String request_id;
    /** 错误码 **/
    private int error_code;
    
    /** 创意，返回多条 **/
    private List<APIAdmsVO> adms;
    
    /** 同一view下，自动请求广告的时间间隔, 默认是3600s **/
    private int get_ad_in_same_view_interval = 3600;
    
    /** 同一view下，重复请求广告次数, 默认是3次 **/
    private int get_ad_in_same_view_times = 3;
    /** 过期时间 **/
    private long expiration_time = Constant.AD_EXPIRATION_TIME;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<APIAdmsVO> getAdms() {
        return adms;
    }

    public void setAdms(List<APIAdmsVO> adms) {
        this.adms = adms;
    }

    public int getGet_ad_in_same_view_interval() {
        return get_ad_in_same_view_interval;
    }

    public void setGet_ad_in_same_view_interval(int get_ad_in_same_view_interval) {
        this.get_ad_in_same_view_interval = get_ad_in_same_view_interval;
    }

    public long getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(long expiration_time) {
        this.expiration_time = expiration_time;
    }

	public int getGet_ad_in_same_view_times() {
		return get_ad_in_same_view_times;
	}

	public void setGet_ad_in_same_view_times(int get_ad_in_same_view_times) {
		this.get_ad_in_same_view_times = get_ad_in_same_view_times;
	}
    
}
