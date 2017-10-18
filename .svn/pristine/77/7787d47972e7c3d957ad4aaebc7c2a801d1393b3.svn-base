package com.gionee.sspzx.controller;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.gionee.sspzx.analyze.redis.TestQueryRedisAction;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年3月22日
 */
public class BaseTestSDKController {
	
	//根据is_local_env判断是本地环境还是测试环境
	String url_local="http://localhost:8080/ssppb/v1.1/getad";   //本地环境
	String url_test="http://sspzx.ssptest.gionee.com/v1.1/getad";  //开发环境
	String url_product="http://sspzx.gionee.com/v1.1/getad";  //生产环境
	
	
	//根据is_local_env判断是本地环境还是测试环境
	String ad_query_url_local="http://localhost:8080/gionee-ad-query-adm/adQueryService.do";   //本地环境
	String ad_query_url_test="http://121.41.13.42:8085/gionee-adQuery/adQueryService.do";  //开发环境
	String ad_query_url_product="http://121.41.13.42:8084/gionee-ad-query-adm/adQueryService.do";  //生产环境
	//local:本地环境,test:测试环境，product：生产环境
	protected String  env="local";
	
	/**从map中获取请求的参数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getUri(Map<String,String> map) throws Exception{
		
		String json=map.get("reqjson");
		System.out.println(json);
		
		StringBuffer uri=new StringBuffer();
		uri.append("?reqjson=");
		uri.append(URLEncoder.encode(json,"utf-8"));
		uri.append(this.getPublicParam(map)); //SDK公参
		return uri.toString();
	}
	
	public SdkRequestVO printRequestVo(Map<String,String> map){
		
		System.out.println("=============开始打印请求参数信息===========");
		String json=map.get("reqjson");
		try {
			SdkRequestVO vo = JsonUtils.readJson2Object(json, SdkRequestVO.class);
			System.out.println(vo);
			System.out.println("=============结束打印请求参数信息===========");
			return vo;
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**打印redis的信息
	 * @param vo
	 * @throws Exception 
	 */
	public void printRedisInfo(SdkRequestVO vo) throws Exception{

		System.out.println("=============开始打印redis参数信息===========");
		TestQueryRedisAction t=new TestQueryRedisAction();
		if(env.equals("local")){
			t.setEnv("test");
		}else{
			t.setEnv(env);
		}
		t.setApp_id(vo.getApp().getApp_id());
		t.setAdslot_id(vo.getAdslot().getAdslot_id());
		t.execute();
		System.out.println("=============结束打印redis参数信息===========");
	}
	/**发送http请求
	 * @param uri
	 * @throws Exception
	 */
	public void httpGet(String uri) throws Exception{
		
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse  response=null;
		httpclient = HttpClients.createDefault();    
		
		String url=this.getUrl();
        
        String url_enc=url+uri;
        System.out.println(url_enc);
        HttpGet httpGet = new HttpGet(url_enc); 
        
        try{
        response = httpclient.execute(httpGet); 
        // 获取响应实体      
        HttpEntity entity = response.getEntity();  
        // 打印响应状态      
        System.out.println(response.getStatusLine().getStatusCode());    
        if (entity != null) {    
            // 打印响应内容      
        	String rsp_json=EntityUtils.toString(entity);
            System.out.println("Response content: " + rsp_json);
            SdkResponseVO vo = JsonUtils.readJson2Object(rsp_json, SdkResponseVO.class);
            System.out.println(vo);
            if(vo.getAdms()!=null){
            	System.out.println("返回广告条数->"+vo.getAdms().size());
            }
        }  
        } catch (Exception e) {
			e.printStackTrace();
		}finally{  
            httpclient.close();  
            response.close();  
        }  
	}
	/**组装公参
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getPublicParam(Map<String,String> map) throws Exception{
		String svr = map.get("svr"); //SDK版本
		String device = map.get("device_n"); //日志中使用的是device_n
		if(StringUtils.isEmpty(device)){
			device = map.get("device");  //SDK中使用的是device
		}
		String cuid = map.get("cuid");
		String client_id = map.get("client_id");
		String device_id = map.get("device_id");
		if(StringUtils.isEmpty(device_id)){
			device_id="";
		}
		return 	  "&channel_id=3"
				+ "&device="+ URLEncoder.encode(device,"utf-8")
				+ "&cuid="+ URLEncoder.encode(cuid,"utf-8")
				+ "&client_id=" + URLEncoder.encode(client_id,"utf-8")
				+ "&device_id="+ URLEncoder.encode(device_id,"utf-8")
				+ "&os_level=24"
				+ "&sn=SSP_SDK"
				+ "&svr="+svr;
	}
	public String getUrl(){
		if(this.env.equals("test")){
			return url_test;
		}else if(this.env.equals("product")){
			return url_product;
		}
		return url_local;
	}
	
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	
}
