
package com.gionee.brocast;  
  
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
  
/**
 * @author dingyw
 *
 * 2017年4月20日
 */
public class TestAsyncHttpPost {  
	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    private static final String URL="http://121.41.13.42:8085/gionee-adQuery/adQueryService.do";
    
    public static void main(String[] args) throws Exception {  
    	TestAsyncHttpPost.multiPost();
    }  
  
    public static void onePost()throws Exception{
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        final HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        StringEntity entity = new StringEntity(TestAsyncHttpPost.getJson());
        entity.setContentType(CONTENT_TYPE_TEXT_JSON);
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(entity);
        final Future<HttpResponse> future = httpClient.execute(httpPost, null);
        try {
            HttpResponse response = (HttpResponse) future.get();
            System.out.println("Response:" + response.getStatusLine());
            HttpEntity rsp_entity = response.getEntity();
            if (rsp_entity != null) {
            	String result=EntityUtils.toString(rsp_entity, StandardCharsets.UTF_8);
                System.out.println(result);
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
    
    public static String getJson(){
    	return "{\"trans_code\":\"100001\",\"req_sys\":\"0001\",\"req_date\":\"20170420\",\"req_time\":\"20170420100456\",\"body\":{\"redis_server_name\":\"ssp\",\"key\":\"wk_SSP_2668_3335\"},\"sign\":\"8956B68659729E2749A3CEC6A65D859C\"}";
    }

	public static void multiPost() throws Exception{
        final RequestConfig requestConfitg = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfitg)
                .build();
        
        httpClient.start();
        
        final HttpPost[] http_posts = new HttpPost[]{
        		getHttpPost(),
        		getHttpPost(),
        		getHttpPost()
        };
        
        final CountDownLatch latch = new CountDownLatch(http_posts.length);
        for(final HttpPost post: http_posts){
                httpClient.execute(post, new FutureCallback<HttpResponse>(){
                    @Override
                    public void completed(HttpResponse obj) {
                       final HttpResponse response = (HttpResponse)obj;
                       latch.countDown();
                       System.out.println(post.getRequestLine() + "->" + response.getStatusLine());
                       System.out.println("Response:" + response.getStatusLine());
                       HttpEntity rsp_entity = response.getEntity();
                       if (rsp_entity != null) {
                       	String result;
						try {
							result = EntityUtils.toString(rsp_entity);
							 System.out.println(result);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                          
                       }
                    }

                    @Override
                    public void failed(Exception excptn) {
                        latch.countDown();
                        System.out.println(post.getRequestLine() + "->" + excptn);
                    }

                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(post.getRequestLine() + "cancelled");
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
	public static HttpPost getHttpPost() throws Exception{
		HttpPost httpPost = new HttpPost(URL);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        StringEntity entity = new StringEntity(TestAsyncHttpPost.getJson());
        entity.setContentType(CONTENT_TYPE_TEXT_JSON);
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(entity);
        return httpPost;
	}
}  