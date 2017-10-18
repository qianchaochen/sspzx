package com.gionee.sspzx.analyze.redis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestBaseAction {

	// 根据is_local_env判断是本地环境还是测试环境
	String url_local = "http://localhost:8080/gionee-ad-query-adm/adQueryService.do"; // 本地环境
	String url_test = "http://121.41.13.42:8085/gionee-adQuery/adQueryService.do"; // 开发环境
	String url_product = "http://121.41.13.42:8084/gionee-ad-query-adm/adQueryService.do"; // 生产环境

	protected String trans_code;

	protected String req_sys = "0001";
	// local:本地环境,test:测试环境，product：生产环境
	protected String env = "local";

	protected String secret_key_local = "12345678";
	protected String secret_key_test = "12345678";
	protected String secret_key_product = "GioneeRedisMD5Key2017$!";

	public Map<String,Object> getHeader() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
		Map <String,Object> jsonReq = new HashMap<String,Object>();
		jsonReq.put("trans_code", trans_code);
		jsonReq.put("req_sys", req_sys);
		jsonReq.put("req_date", sdf.format(date));
		jsonReq.put("req_time", sdf2.format(date));
		return jsonReq;
	}

	public String getUrl() {
		if (this.env.equals("test")) {
			return url_test;
		} else if (this.env.equals("product")) {
			return url_product;
		}
		return url_local;
	}

	public String getSecretKey() {

		String key = null;
		if (this.req_sys.equals("0001")) {
			if (this.env.equals("test")) {
				key = secret_key_test;
			} else if (this.env.equals("product")) {
				key = secret_key_product;
			} else {
				key = secret_key_local;
			}
		}
		return key;
	}

	public void setSign(Map<String,Object> jsonReq, Map<String,String> jsonBody) {
		try {
			String signContent=TestSecurityUtils.listToStr(TestSecurityUtils.getAscList(jsonBody));
			String content=signContent+"&"+this.getSecretKey(); //用传进来的key
			String sign = TestMd5Util.getAmigoMd5(content);

			jsonReq.put("sign", sign);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getReq_sys() {
		return req_sys;
	}

	public void setReq_sys(String req_sys) {
		this.req_sys = req_sys;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

}
