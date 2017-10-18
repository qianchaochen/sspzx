package com.gionee.brocast;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ExceptionLogger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.nio.protocol.BasicAsyncRequestProducer;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequester;
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

import com.gionee.sspzx.analyze.SspLogReqParser;


/**
 * 广播服务实现类
 * @author dingyw
 *
 * 2017年4月20日
 */
@Service
public class TestBroadCastServiceGet {

    private static BasicNIOConnPool pool;
    
    private static final String URL="http://sspzx.ssptest.gionee.com";
   //private static final String URL="http://www.baidu.com";
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
    	TestBroadCastServiceGet t=new TestBroadCastServiceGet();
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

        int cnt=1;
        final CountDownLatch latch = new CountDownLatch(cnt);

        HttpCoreContext coreContext = HttpCoreContext.create();

        // composeHeader(request);
        HttpAsyncRequester requester = new HttpAsyncRequester(httpproc, new DefaultConnectionReuseStrategy(),
                ExceptionLogger.NO_OP);
        
        for (int i=0;i<cnt;i++) {
            try {

                String interface_url = TestBroadCastServiceGet.URL;
                URL url = new URL(interface_url); // 获得DSP的url
               
                int port = url.getPort();
                port = port == -1 ? 80 : port; // 判断是否有prot如果没有，默认是80
                System.out.println(url.getHost());
                
                HttpHost target = new HttpHost(url.getHost(), port, url.getProtocol());
                
              //构造一个parser,分析TestSspLogReq.txt日志
        		SspLogReqParser parser=new SspLogReqParser();
        		Map<String,String> map=parser.getMap();//map中存放各种参数
        		
        		String uri=this.getUri(map); //拼接请求参数
        		
                String url_enc=URL+"/v1.1/getad"+uri;

                System.out.println("url_enc->"+url_enc);
                //to改造：从对应的service中获取BasicHttpEntityEnclosingRequest request
                HttpGet request = new HttpGet(url_enc);

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
									} catch (Exception e) {
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
            latch.await(1500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
    
	public String getUri(Map<String,String> map) throws Exception{
		
		String json=map.get("reqjson");
		System.out.println(json);
		
		StringBuffer uri=new StringBuffer();
		uri.append("?reqjson=");
		uri.append(URLEncoder.encode(json,"utf-8"));
		uri.append(this.getPublicParam(map)); //SDK公参
		return uri.toString();
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

}
