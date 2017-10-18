package com.gionee.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.CharEncoding;

/**
 * @author dingyw
 *
 * 2017年3月25日
 */
public class AmigoHttpUtils {

	private static final int CONNECTION_TIMEOUT = 10000;
	private static final int READ_TIMEOUT = 10000;

	public static String post(String reqUrl, String body) throws IOException {
		URL serverUrl = new URL(reqUrl);
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer();

		try {
			conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.connect();

			conn.getOutputStream().write(body.getBytes(CharEncoding.UTF_8));

			isr = new InputStreamReader(conn.getInputStream(), CharEncoding.UTF_8);
			br = new BufferedReader(isr);

			for (String line = ""; (line = br.readLine()) != null;) {
				buffer.append(line);
			}
		} finally {
			if (null != br) {
				br.close();
			}
			if (null != isr) {
				isr.close();
			}
			if (null != conn) {
				conn.disconnect();
			}
		}

		return buffer.toString();
	}


	public static String getByHttps(String url) throws IOException {
		URL reqUrl = new URL(url);
		HttpsURLConnection conn = null;
		InputStream inStream = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer(1024);

		try {
			conn = (HttpsURLConnection) reqUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setAllowUserInteraction(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");

			inStream = conn.getInputStream();
			isr = new InputStreamReader(inStream, CharEncoding.UTF_8);
			br = new BufferedReader(isr);

			for (String line = ""; (line = br.readLine()) != null;) {
				buffer.append(line);
			}
		} finally {
			if (null != inStream) {
				inStream.close();
			}
			if (null != br) {
				br.close();
			}
			if (null != isr) {
				isr.close();
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 将Map组装成待签名数据。 待签名的数据必须按照一定的顺序排列 这个是支付宝提供的服务的规范，否则调用支付宝的服务会通不过签名验证
	 * 
	 * @param params
	 * @return
	 */
	public static String getSignData(Map<String, String> params) {
		StringBuffer content = new StringBuffer();

		// 按照key做排序
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if ("sign".equals(key) || "sign_type".equals(key)) {
				continue;
			}
			String value = params.get(key);
			if (value != null) {
				content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
			} else {
				content.append(i == 0 ? "" : "&").append(key).append("=");
			}
		}
		return content.toString();
	}

	/**
	 * 将Map中的数据组装成URL
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
		return mapToUrl(params, true, CharEncoding.UTF_8);
	}

	/**
	 * 将Map中的数据组装成URL
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String mapToUrl(Map<String, String> params, boolean urlSafe, String charSet)
			throws UnsupportedEncodingException {
		if (null == params || params.keySet().size() == 0) {
			return ("");
		}

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		Iterator<Entry<String, String>> paramsItor = params.entrySet().iterator();

		while (paramsItor.hasNext()) {
			Entry<String, String> entry = paramsItor.next();
			String value = entry.getValue();

			if (isFirst) {
				sb.append(entry.getKey()).append("=").append(urlSafe ? URLEncoder.encode(value, charSet) : value);
				isFirst = false;
			} else {
				if (value != null) {
					sb.append("&").append(entry.getKey()).append("=")
							.append(urlSafe ? URLEncoder.encode(value, charSet) : value);
				} else {
					sb.append("&").append(entry.getKey()).append("=");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 取得URL中的参数值。
	 * <p>
	 * 如不存在，返回空值。
	 * </p>
	 * 
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getParameter(String url, String name) {
		if (name == null || name.equals("")) {
			return null;
		}
		name = name + "=";

		int start = url.indexOf(name);
		if (start < 0) {
			return null;
		}
		start += name.length();
		int end = url.indexOf("&", start);
		if (end == -1) {
			end = url.length();
		}
		return url.substring(start, end);
	}

	public static String get(String url) throws IOException {
		URL reqUrl = new URL(url);
		HttpURLConnection conn = null;
		InputStream inStream = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer(1024);

		try {
			conn = (HttpURLConnection) reqUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setAllowUserInteraction(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");

			inStream = conn.getInputStream();
			isr = new InputStreamReader(inStream, CharEncoding.UTF_8);
			br = new BufferedReader(isr);

			for (String line = ""; (line = br.readLine()) != null;) {
				buffer.append(line);
			}
		} finally {
			if (null != inStream) {
				inStream.close();
			}
			if (null != br) {
				br.close();
			}
			if (null != isr) {
				isr.close();
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
		return buffer.toString();
	}
}
