package com.wk.ssp.utils.http;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import com.gionee.ssp.thread.connection.IdleConnectionMonitorThread;

/**
 * @description: http连接池工具类
 */
public class HttpPoolUtils {

    /* httpclient 连接池实现 */
    private static PoolingHttpClientConnectionManager cm;

    private static IdleConnectionMonitorThread cleaner;

    private static int defaultMax;

    private static int defaultRout;

    /**
     * @title: getConnectionKeepAliveStrategy
     * @description: 长连接策略实现
     * @param: keepAliveTime 客户端保持长连接的时间
     * @return: ConnectionKeepAliveStrategy
     * @throws:
     */
    public static ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy(final long keepAliveTime) {

        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // 遍历response的header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));

                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {// 如果头部包含timeout信息，则使用
                        try {
                            // 超时时间设置为服务器指定的值
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {

                        }
                    }
                }

                // 获取主机,可根据访问的主机名设置不同的长连接时间
                // HttpHost target = (HttpHost)
                // context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);

                // if
                // ("webservice.webxml.com.cn".equalsIgnoreCase(target.getHostName()))
                // {
                // // 如果访问webservice.webxml.com.cn主机则设置长连接时间为5秒
                // return 5 * 1000;
                // } else {
                // // 其他为30秒
                // return 30 * 1000;
                // }
                return keepAliveTime;
            }

        };

        return myStrategy;
    }

    /**
     * @title: getCm
     * @description: 获取http连接池
     * @param: maxTotal 连接池最大生成数。若连接池没有初始化，初始化改属性 @parpm： defaultMaxPerRoute
     *             设置route最大连接数。若连接池没有初始化，初始化改属性
     * @return: PoolingHttpClientConnectionManager
     * @throws:
     */
    public static PoolingHttpClientConnectionManager getCm(int maxTotal, int defaultMaxPerRoute) {

        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            // 连接池最大生成连接数
            cm.setMaxTotal(maxTotal);
            // 默认设置route最大连接数
            cm.setDefaultMaxPerRoute(defaultMaxPerRoute);

        }
        return cm;
    }

    public static PoolingHttpClientConnectionManager getCm() {

        if (cm == null) {
            getCm(defaultMax, defaultRout);
        }
        return cm;
    }

    public static void poolInstence() {
        cm = new PoolingHttpClientConnectionManager();
        // 连接池最大生成连接数
        cm.setMaxTotal(defaultMax);
        // 默认设置route最大连接数
        cm.setDefaultMaxPerRoute(defaultRout);
    }

    /**
     * @title: setMaxPerRouteForHost
     * @description: 设置指定route的最大连接数
     * @param: IP 目标IP
     * @param: port 目标端口
     * @param: num 最大连接数
     * @return: void
     * @throws:
     */
    public static void setMaxPerRouteForHost(String IP, int port, int linkNum) {
        // 指定专门的route，设置最大连接数
        HttpHost host = new HttpHost(IP, port);
        cm.setMaxPerRoute(new HttpRoute(host), linkNum);
    }

    /**
     * @title: getThreadCleaner
     * @description: 获取连接池cleaner
     * @param: connMgr
     * @return: IdleConnectionMonitorThread
     * @throws:
     */
    public static IdleConnectionMonitorThread getThreadCleaner(PoolingHttpClientConnectionManager connMgr,
            long cleanTime, int timeout) {

        if (cleaner == null || cleaner.getClass() != IdleConnectionMonitorThread.class) {
            cleaner = new IdleConnectionMonitorThread(connMgr, cleanTime, timeout);
        }

        return cleaner;
    }

    /**
     * @param defaultMax the defaultMax to set
     */
    public static void setDefaultMax(int defaultMax) {
        HttpPoolUtils.defaultMax = defaultMax;
    }

    /**
     * @param defaultRout the defaultRout to set
     */
    public static void setDefaultRout(int defaultRout) {
        HttpPoolUtils.defaultRout = defaultRout;
    }

}
