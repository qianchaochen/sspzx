
package com.gionee.brocast;  
  
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import com.gionee.sspzx.analyze.SspLogReqParser;
  
/**
 * @author dingyw
 *
 * 2017年4月20日
 */
public class TestAsyncHttpGetAd {  
	 private static final String URL="http://sspzx.ssptest.gionee.com";
  
    public static void main(String[] args) throws Exception {  
    	TestAsyncHttpGetAd.oneRequest();
    }  
  
    public static void oneRequest() throws Exception{
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
       
        SspLogReqParser parser=new SspLogReqParser();
		Map<String,String> map=parser.getMap();//map中存放各种参数
		
		String uri=getUri(map); //拼接请求参数
		
	      String url_enc=URL+"/v1.1/getad"+uri;
		 final HttpGet request = new HttpGet(url_enc);
		 
        final Future<HttpResponse>  future = httpClient.execute(request, null);
        try {
            HttpResponse response = (HttpResponse) future.get();
            System.out.println("Response:" + response.getStatusLine());
            HttpEntity rsp_entity = response.getEntity();
            if (rsp_entity != null) {
            	String result;
				try {
					result = EntityUtils.toString(rsp_entity, StandardCharsets.UTF_8);
					 System.out.println(result);
				} catch (Exception e) {
					e.printStackTrace();
				} 
               
            }
            System.out.println("Shutting down");
        } catch (Exception ex) {
           
        }finally{
            try {
                httpClient.close();
            } catch (IOException ex) {
                
            }
        }
        
        System.out.println("执行完毕");
    }
	public static void moreRequest(){
        final RequestConfig requestConfitg = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfitg)
                .build();
        
        httpClient.start();
        
        final HttpGet[] requests = new HttpGet[]{
            new HttpGet("http://www.qq.com/"),
            new HttpGet("http://www.baidu.com/"),
            new HttpGet("http://www.sina.com/")
        };
        
        final CountDownLatch latch = new CountDownLatch(requests.length);
        for(final HttpGet request: requests){
                httpClient.execute(request, new FutureCallback<HttpResponse>(){
                    @Override
                    public void completed(HttpResponse obj) {
                       final HttpResponse response = (HttpResponse)obj;
                       latch.countDown();
                       System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                    }

                    @Override
                    public void failed(Exception excptn) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->" + excptn);
                    }

                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "cancelled");
                    }
                });
         }       
        
        try {
            latch.await();
            System.out.println("Shutting Down");
        } catch (InterruptedException ex) {
           
        }finally{
            try {
                httpClient.close();
            } catch (IOException ex) {
               
            }
        }
        System.out.println("Finish!");
    }
	public static String getUri(Map<String,String> map) throws Exception{
		
		String json=map.get("reqjson");
		System.out.println(json);
		
		StringBuffer uri=new StringBuffer();
		uri.append("?reqjson=");
		uri.append(URLEncoder.encode(json,"utf-8"));
		uri.append(getPublicParam(map)); //SDK公参
		return uri.toString();
	}
	/**组装公参
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String getPublicParam(Map<String,String> map) throws Exception{
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

}  