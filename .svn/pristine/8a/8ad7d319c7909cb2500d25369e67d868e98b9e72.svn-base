package com.gionee.sspzx.analyze.redis;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gionee.common.utils.AmigoHttpUtils;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年3月23日
 */
public class TestQueryRedisAction extends TestBaseAction{
	
	/**	
	 * 应用ID
	 */
	private String app_id;
	
	/**
	 * 广告位ID
	 */
	private String adslot_id;
	/**
	 * @throws Exception
	 */
	public void execute() throws Exception {
		//是否采用本地环境还是测试环境
		if(!"local".equals(this.env)){
			this.setEnv(env);
		}else{
			this.setEnv("test");
		}
		String url=this.getUrl();
		
		this.setTrans_code("100001");
		Map<String,Object> jsonReq = this.getHeader();
		
		Map<String,String> jsonBody = new HashMap<String,String>();
		
		jsonBody.put("redis_server_name", "ssp");
		jsonBody.put("key", "wk_ssp_count_queue_list");
		//jsonBody.put("key", "wk_SSP_"+app_id+"_"+adslot_id);
		jsonReq.put("body", jsonBody);
		
		this.setSign(jsonReq, jsonBody);
		
		String req_plain=JsonUtils.writeObject2Json(jsonReq);
		System.out.println(req_plain);
	try {
		String response=AmigoHttpUtils.post(url, req_plain);
		System.out.println("response:"+response);
		this.printVal(response); //获取到返回里面的redis值

	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public static void main(String[] args) throws Exception {
		TestQueryRedisAction t=new TestQueryRedisAction();

		t.execute();
	}
	private void printVal(String response) throws Exception{
		@SuppressWarnings("unchecked")
		Map<String, String> maps=JsonUtils.readJson2Object(response, Map.class);
		
		Object body=maps.get("body");
		if(!StringUtils.isEmpty(body.toString()) ){
			
			String json_str=JsonUtils.writeObject2Json(body);
			@SuppressWarnings("unchecked")
			Map<String, String> json=JsonUtils.readJson2Object(json_str, Map.class);
			
			String val=(String)json.get("val");
			if(!StringUtils.isEmpty(val)){
				FillingDataVO result = JsonUtils.readJson2Object(json.get("val").toString(), FillingDataVO.class);
				System.out.println(result);
			}else{
				System.out.println("找不到对应的值");
			}
		}
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getAdslot_id() {
		return adslot_id;
	}

	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}
	
}
