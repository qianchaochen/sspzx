package com.gionee.brocast;

import java.io.IOException;

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

/**http连接池管理类
 * @author dingyw
 *
 * 2017年4月20日
 */
public class TestHttpPoolManager {
    private TestHttpPoolManager() {

    }

    /**
     * 得到{@link TestHttpPoolManager}的实例
     * 
     * @return 返回{@link TestHttpPoolManager}的实例
     */
    public static TestHttpPoolManager getInstance() {
        return instance;
    }

    private static int AVAIL_PROCS = Runtime.getRuntime().availableProcessors();
    private static TestHttpPoolManager instance = new TestHttpPoolManager();

    private static IOReactorConfig getIOReactorConfig() {
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

    private static BasicNIOConnPool pool;

    /**
     * 获取连接池
     * 
     * @return 返回连接池
     * @throws IOReactorException 抛出异常
     */
    public BasicNIOConnPool getPool() throws IOReactorException {
        if (pool != null) {
            return pool;
        }
        final ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(getIOReactorConfig());
        HttpAsyncRequestExecutor protocolHandler = new HttpAsyncRequestExecutor();
        ConnectionConfig connectionConfig = ConnectionConfig.custom().build();
        // Create client-side I/O event dispatch
        final IOEventDispatch ioEventDispatch = new DefaultHttpClientIODispatch(protocolHandler, connectionConfig);
        // Create HTTP connection pool
        pool = new BasicNIOConnPool(ioReactor, new BasicNIOConnFactory(connectionConfig), 200);
        // Limit total number of connections to just two
        pool.setDefaultMaxPerRoute(10);
        pool.setMaxTotal(100);
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
        return pool;
    }

    public void destroy() throws IOException {
        pool.shutdown(2000);
    }
}
