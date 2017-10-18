package com.gionee.brocast;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.ExceptionLogger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.protocol.BasicAsyncRequestProducer;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


/**
 * 广播服务实现类
 * @author dingyw
 *
 * 2017年4月20日
 */
@Service
public class TestBroadCastServicePost2 {

    private static BasicNIOConnPool pool;
    
    private static final String URL="http://121.41.13.42:8085/gionee-adQuery/adQueryService.do";
	private static final String APPLICATION_JSON = "application/json";
    private final String CONTENT_TYPE="application/x-protobuf";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    /**
     * 服务初始化操作
     * 
     * @throws Exception 初始化的异常
     */
    public void init() throws Exception {
        pool = TestHttpPoolManager.getInstance().getPool();
    }

    /**
     * 服务销毁操作
     * 
     * @throws Exception 销毁的异常
     */
    public void destroy() throws Exception {
    	TestHttpPoolManager.getInstance().destroy();
    };

    public static void main(String[] args) throws Exception {
    	TestBroadCastServicePost2 t=new TestBroadCastServicePost2();
    	t.init();
    	t.broadCast();
    	
    	t.destroy();
	}

    /**
     * @param fillingDataVO
     * @param requestVO
     * @param wkSSPRequest
     * @param adx_list
     */
    public void broadCast() {

        // 构造传输pb报文的Header头
        Collection<Header> defaultHeaders = new HashSet<Header>();
/*        defaultHeaders.add(new BasicHeader("Content-Type", "application/x-protobuf"));
        defaultHeaders.add(new BasicHeader("Accept", "application/x-protobuf"));*/
        
        HttpProcessor httpproc = HttpProcessorBuilder.create()
        		.add(new RequestContent(true))
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent())
                .add(new RequestExpectContinue(true))
                .add(new RequestDefaultHeaders(defaultHeaders))
                .build();

        int cnt=10;
        final CountDownLatch latch = new CountDownLatch(cnt);

        HttpCoreContext coreContext = HttpCoreContext.create();

        // composeHeader(request);
        HttpAsyncRequester requester = new HttpAsyncRequester(httpproc, new DefaultConnectionReuseStrategy(),
                ExceptionLogger.NO_OP);
        for (int i=0;i<cnt;i++) {
            try {

                String interface_url = TestBroadCastServicePost2.URL;
                URL url = new URL(interface_url); // 获得DSP的url
               
                int port = url.getPort();
                port = port == -1 ? 80 : port; // 判断是否有prot如果没有，默认是80
                
                HttpHost target = new HttpHost(url.getHost(), port, url.getProtocol());

                //to改造：从对应的service中获取 request
                HttpPost request = new HttpPost(interface_url);
                request.addHeader("Content-Type", CONTENT_TYPE);
                request.addHeader("Accept", CONTENT_TYPE);
                
                //或者：  HttpPost request = new HttpPost(interface_url);
                StringEntity entity = new StringEntity(TestAsyncHttpPost.getJson());
                entity.setContentType(CONTENT_TYPE_TEXT_JSON);
                entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
                request.setEntity(entity);

                System.out.println("begin to req");
                // 执行请求
                requester.execute(new BasicAsyncRequestProducer(target, request), new BasicAsyncResponseConsumer(),
                        pool, coreContext,
                        // Handle HTTP response from a callback
                        new FutureCallback<HttpResponse>() {

                            public void completed(final HttpResponse response) {
                                // 程序执行完成进行减操作
                                System.out.println("Response:" + response.getStatusLine());
                                HttpEntity rsp_entity = response.getEntity();
                                if (rsp_entity != null) {
                                	String result;
									try {
										result = EntityUtils.toString(rsp_entity, StandardCharsets.UTF_8);
										 System.out.println(result);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
                                   
                                }
                                latch.countDown();
                            }

                            public void failed(final Exception ex) {
                            	System.out.println("failed");
                                latch.countDown();
                            }

                            public void cancelled() {
                            	System.out.println("cancel");
                                latch.countDown();
                            }
                        });

            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        try {
            latch.await(1200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }

}
