package com.gionee.ssp.service.http.pool;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.DefaultHttpClientIODispatch;
import org.apache.http.impl.nio.pool.BasicNIOConnFactory;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.HttpAsyncRequestExecutor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**http连接池管理类
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class HttpPoolManager {
	//ssp向ADX广播参数控制   
    /** {@code SSP}向{@code ADX}发送广播的过期时间 **/
	 @Value("#{ad_flow_config.TIMEOUT}")
    public long TIMEOUT;

    /** 请求分发的最大连接数 **/
    @Value("#{ad_flow_config.MAX_TOTAL}")
    public int MAX_TOTAL;

    /** 分发每个路由的最大连接数 **/
	 @Value("#{ad_flow_config.DEFAULT_MAX_PER_ROUTE}")
    public int DEFAULT_MAX_PER_ROUTE;
    
    /**
     * 异步http连接池
     */
    private  BasicNIOConnPool pool;
    
    private int AVAIL_PROCS = Runtime.getRuntime().availableProcessors();


    private  IOReactorConfig getIOReactorConfig() {
        org.apache.http.impl.nio.reactor.IOReactorConfig.Builder builder = IOReactorConfig.custom();
        builder.setSelectInterval(100);
        builder.setShutdownGracePeriod(300);
        builder.setInterestOpQueued(true);
        builder.setIoThreadCount(AVAIL_PROCS);
        builder.setSoTimeout(1000);
        builder.setSoLinger(-1);
        builder.setSoKeepAlive(true);
        builder.setTcpNoDelay(true);
        builder.setConnectTimeout(1000);
        int bufferSize = 2048;
        builder.setSndBufSize(bufferSize);
        builder.setRcvBufSize(bufferSize);
        builder.setBacklogSize(bufferSize);
        return builder.build();
    }

    /**
     * 获取连接池
     * 
     * @return 返回连接池
     * @throws IOReactorException 抛出异常
     */
    @PostConstruct
    public void init() throws IOReactorException {
        final ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(getIOReactorConfig());
        HttpAsyncRequestExecutor protocolHandler = new HttpAsyncRequestExecutor();
        ConnectionConfig connectionConfig = ConnectionConfig.custom().build();
        // Create client-side I/O event dispatch
        final IOEventDispatch ioEventDispatch = new DefaultHttpClientIODispatch(protocolHandler, connectionConfig);
        // Create HTTP connection pool
        pool = new BasicNIOConnPool(ioReactor, new BasicNIOConnFactory(connectionConfig), (int) TIMEOUT);
        // Limit total number of connections to just two
        pool.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        pool.setMaxTotal(MAX_TOTAL);
        // Run the I/O reactor in a separate thread
        new Thread(new Runnable() {
            public void run() {
                try {
                    // Ready to go!
                    ioReactor.execute(ioEventDispatch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public BasicNIOConnPool getPool(){
    	 return pool;
    }
    @PreDestroy
    public void destroy() throws IOException {
        pool.shutdown(2000);
    }
}
