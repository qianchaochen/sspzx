
package com.gionee.brocast;  
  
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
  
/**
 * @author dingyw
 *
 * 2017年4月20日
 */
public class TestAsyncHttpGet {  
  
    public static void main(String[] args) throws Exception {  
    	TestAsyncHttpGet.moreRequest();
    }  
  
    public static void oneReuest(){
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        final HttpGet request = new HttpGet("http://www.baidu.com/");
        final Future<HttpResponse>  future = httpClient.execute(request, null);
        try {
            HttpResponse response = (HttpResponse) future.get();
            System.out.println("Response:" + response.getStatusLine());
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
}  