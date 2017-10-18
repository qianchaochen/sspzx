package com.wk.ssp.utils.http;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

/**
 * @description: http工具类
 */
public class WKHttpUtils {

    // 默认Http客户端
    private static CloseableHttpClient defultClient;

	/**
	 * @title: getClient
	 * @description: 生成httpclient客户端
	 * @return
	 */
	public static HttpClient getClient(){
		return HttpClients.createDefault();
	}
	
	/**
	 * @title: getPost
	 * @description: 返回HttpPost对象
	 * @param uri
	 * @return
	 */
	public static HttpPost getPost(String uri){
		return new HttpPost(uri);
	}
	
	/**
	 * @title: getHttpGet
	 * @description: 返回HttpGet对象
	 * @param uri
	 * @return
	 */
	public static HttpGet getHttpGet(String uri){
		return new HttpGet(uri);
	}
	
	/**
	 * @title: setEntity
	 * @description: 加入body体
	 * @param post
	 * @param data
	 */
	public static void setEntity(HttpPost post, byte[] data){
		post.setEntity(new ByteArrayEntity(data));
	}
	
	/**
	 * @title: converToStream
	 * @description: 将entity转换成String
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String converToStream(HttpEntity entity) throws ParseException, IOException{
		return converToStream(entity, Charset.defaultCharset());
	}
	
	/**
	 * @title: converToStream
	 * @description: entity按charset编码转化
	 * @param entity
	 * @param charset
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String converToStream(HttpEntity entity, Charset charset) throws ParseException, IOException{
		return EntityUtils.toString(entity, charset);
	}
	

    /**发送HTTP请求
     * @param requestMothed
     * @return
     * @throws Exception
     */
    public static HttpEntity sendHttp(HttpRequestBase requestMothed) throws Exception {
        if (defultClient == null) {
            initClilent();
        }
        HttpResponse response = defultClient.execute(requestMothed);
        int code = response.getStatusLine().getStatusCode();
        if (!(200 == code)) {
            requestMothed.abort();
            response = null;
            throw new Exception("adx request has error. Status: " + code);
        }
        return response.getEntity();
	}
	
	/**
	 * @title: getIpAddr
	 * @description: 获取目标公网IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		int index = ip.indexOf(",");
        if (index > 0) {
            ip = ip.substring(0, index);
        }
		return ip;
	}

    /**
     * 初始化客户端
     * 
     * @throws IOReactorException
     */
    public static void initClilent() throws IOReactorException {
        if (defultClient == null) {
            PoolingHttpClientConnectionManager cm = HttpPoolUtils.getCm();
            defultClient = HttpClients.custom().setConnectionManager(cm).build();
        }
    }

    /**
     * 关闭客户端
     * 
     * @throws IOException
     */
    public static void closeClient() throws IOException {
        if (defultClient != null) {
            defultClient.close();
        }
    }
}
