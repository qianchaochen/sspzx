
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
import org.apache.http.util.EntityUtils;
  
/**
 * @author dingyw
 *
 * 2017年4月20日
 */
public class TestAsyncHttpPostInmobi {  
	private static final String CONTENT_TYPE = "application/json";
    
    private static final String URL="http://api.w.inmobi.cn/showad/v3";
    
    public static void main(String[] args) throws Exception {  
    	TestAsyncHttpPostInmobi.multiPost();
    }  
  
    public static void onePost()throws Exception{
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        final HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("Content-Type", CONTENT_TYPE);
        httpPost.addHeader("Accept", CONTENT_TYPE);
        
        StringEntity entity = new StringEntity(TestAsyncHttpPostInmobi.getJson());
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
		return "{\"app\":{\"id\":\"1488555072971\",\"bundle\":\"com.wow.locker\"},\"ext\":{\"responseformat\":\"json\"},\"imp\":{\"native\":{\"layout\":0},\"trackertype\":\"url_ping\"},\"device\":{\"iem\":\"000000000000000\",\"geo\":{\"accu\":0,\"lon\":-1,\"lat\":-1},\"ext\":{\"orientation\":1},\"o1\":\"b1a691cc79924b798e76089d944fea7981a3a867\",\"carrier\":\"\",\"ip\":\"111.142.77.104\",\"um5\":\"58c7ed0d1314b35ada5d0d49669839a6\",\"ua\":\"Mozilla/5.0 (Linux; U; Android 5.1; zh-cn;GiONEE-GN9008/GN9008 Build/IMM76D) AppleWebKit534.30(KHTML,like Gecko)Version/4.0 Mobile Safari/534.30 Id/FD34645D0CF3A18C9FC4E2C49F11C510 RV/5.0.16 Env/test\",\"connectiontype\":2}}";
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
        httpPost.addHeader("Content-Type", CONTENT_TYPE);
        httpPost.addHeader("Accept", CONTENT_TYPE);
        
        StringEntity entity = new StringEntity(TestAsyncHttpPostInmobi.getJson());
        httpPost.setEntity(entity);
        return httpPost;
	}
}  