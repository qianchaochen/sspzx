package com.wk.ssp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @description 
 * @author wuxing
 * @date 2017年4月5日
 *
 */
public class GetConfigUtils {
	
	public static String getConfig(String fileNamePath,String name) throws IOException {
	 Properties props = new Properties();  
     InputStream in = null;  
     try {  
         in = new FileInputStream(fileNamePath);  
         // 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中  
         //in = propertiesTools.class.getResourceAsStream(fileNamePath);  
         props.load(in);  
         String value = props.getProperty(name);
         // 有乱码时要进行重新编码  
         // new String(props.getProperty("name").getBytes("ISO-8859-1"), "GBK");  
         return value;  

	     } catch (IOException e) {
	         e.printStackTrace();  
	         return null;  
	     } finally {
	         if (null != in)  
	             in.close();  
	     }  
	}  
	public static String getConfigur(String strName,String id) throws IOException{
		String str = getConfig(Thread.currentThread()
				.getContextClassLoader().getResource("adx.properties").toString().substring(6),strName);
	   	 return getAppConfig(id, str);
	}
	private static String getAppConfig(String cid, String str) {
		Map<String,String> map=new HashMap<String,String>();
	     String[] arrcid=str.split("\\|");
	     for(int i=0;i<arrcid.length;i++){
	    	 String[] str1=arrcid[i].trim().split("-");
	    	 map.put(str1[0].trim(), str1[1].trim());
	     }
	     if(map.get(cid)==null){
	    	 return "";
	     }
		return map.get(cid);
	}
}
