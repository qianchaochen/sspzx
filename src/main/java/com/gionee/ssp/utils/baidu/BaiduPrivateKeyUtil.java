package com.gionee.ssp.utils.baidu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengkun
 *
 * 2016年12月16日
 */
public class BaiduPrivateKeyUtil {
	protected final static Logger errorLogger = LoggerFactory.getLogger("errorLogger");

	public static String readPemFile(String filePath) {
		StringBuffer sb = new StringBuffer();
		try {
			String encoding = "gbk";
			File file = new File(filePath);
			if (file.isFile()){
				System.out.println(1);
			}
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {
					//每行后面加上换行符，否则private key解析会出错
					sb.append(lineTxt+"\n");
				}
				read.close();
			} else {
				errorLogger.error("找不到百度私钥文件" + filePath);
				throw new FileNotFoundException("百度PRIVATE KEY文件未找到|"+filePath);
			}
		} catch (Exception e) {
			errorLogger.error("读取百度私钥文件出错" + e);
			throw new RuntimeException("读取百度PRIVATE KEY文件出错|"+e.getMessage());
		}

		return sb.toString();
	}
}
