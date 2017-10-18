package com.gionee.ssp.service.broadcast.impl;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.ExceptionLogger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.gionee.ssp.service.broadcast.AdxRouteService;
import com.gionee.ssp.service.broadcast.BroadCastService;
import com.gionee.ssp.service.http.pool.HttpPoolManager;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;


/**
 * 广播服务实现类
 * @author dingyw
 *
 * 2017年4月20日
 */
@Service
public class BroadCastServiceImpl implements BroadCastService {

     
	/**
	 * http连接池管理
	 */
	@Autowired
	HttpPoolManager httpPoolManager;
    
    /**
     * 根据adx_name，找到对应的adx服务层，然后调用相关的服务
     */
    @Autowired
    AdxRouteService adxRouteService;
    
    
    /** {@code SSP}向{@code ADX}发送广播的过期时间 **/
	 @Value("#{ad_flow_config.TIMEOUT}")
    public long TIMEOUT;


    /**初始化结果集
     * @param adx_list
     * @return
     */
    private Map<String, SdkResponseVO> initMap(final List<AdxInfoVO> adx_list) {
        Map<String, SdkResponseVO> requestMap = new HashMap<String, SdkResponseVO>();
        for (int i=0;i<adx_list.size();i++) {
        	AdxInfoVO adxInfoVO=adx_list.get(i);
            requestMap.put(adxInfoVO.getAdxName(), null);
        }
        return requestMap;
    }
    /**
     * @param fillingDataVO
     * @param requestVO
     * @param wkSSPRequest
     * @param adx_list
     */
    @Override
    public Map<String, SdkResponseVO> broadCastBidRequest(FillingDataVO fillingDataVO,SdkRequestVO requestVO,List<AdxInfoVO> adx_list) {

    	//初始化结果返回
        Map<String, SdkResponseVO> requestMap = this.initMap(adx_list);
        // 构造传输pb报文的Header头
        Collection<Header> defaultHeaders = new HashSet<Header>();
        
        HttpProcessor httpproc = HttpProcessorBuilder.create()
        		.add(new RequestContent(true))
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent())
                .add(new RequestExpectContinue(true))
                .add(new RequestDefaultHeaders(defaultHeaders))
                .build();

        final CountDownLatch latch = new CountDownLatch(adx_list.size());

        HttpCoreContext coreContext = HttpCoreContext.create();
        
        // composeHeader(request);
        HttpAsyncRequester requester = new HttpAsyncRequester(httpproc, new DefaultConnectionReuseStrategy(),
                ExceptionLogger.NO_OP);

        for (int i=0;i<adx_list.size();i++) {
            try {
            	AdxInfoVO adxInfoVO = adx_list.get(i);
                
            	String interface_url = adxInfoVO.getAdxUrl();
                
            	URL url = new URL(interface_url); // 获得DSP的url
               
                int port = url.getPort();
                
                HttpHost target = new HttpHost(url.getHost(), port, url.getProtocol());
                coreContext.setTargetHost(target);
                //根据adx_name，实际获取的是ToutiaoHttpService等具体实例
                AdBroadcastService adBroadcastHttpService=adxRouteService.getAdBroadcastHttpService(adxInfoVO.getAdxName());
                
                //to改造：从对应的service中获取 request
                HttpRequestBase request = adBroadcastHttpService.getHttpRequest(requestVO,fillingDataVO,adxInfoVO);
                
                // 执行请求
                requester.execute(new BasicAsyncRequestProducer(target, request), new BasicAsyncResponseConsumer(),
                		httpPoolManager.getPool(), coreContext,
                        // Handle HTTP response from a callback
                        new FutureCallback<HttpResponse>() {

                            public void completed(final HttpResponse response) {
                            	
                            	HttpEntity entity = response.getEntity();
								try {
									SdkResponseVO sdkResponseVO = adBroadcastHttpService.getRsp(entity,fillingDataVO,requestVO);
	                            	if(null != sdkResponseVO){
	                            		requestMap.put(adxInfoVO.getAdxName(), sdkResponseVO);
	                            	}
								} catch (Exception e) {
									e.printStackTrace();
								}
                            	
                                // 程序执行完成进行减操作
                                latch.countDown();
                            }

                            public void failed(final Exception ex) {
                                latch.countDown();
                            }

                            public void cancelled() {
                                latch.countDown();
                            }
                        });

            } catch (Exception e) {
                WKLogManager.getLOG().addSysErrorLog(e);
            }
        }
        try {
            latch.await(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            WKLogManager.getLOG().addSysErrorLog(e);
        }
        return requestMap;
    }

}
