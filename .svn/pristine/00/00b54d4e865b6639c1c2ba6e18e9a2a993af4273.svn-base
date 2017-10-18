package com.gionee.sspzx.analyze;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gionee.common.FileUtils;

/**读取并且分析TestReqEncode.txt的内容，然后构造请求参数
 * TestReqEncode.txt为SDK发出的日志，经过了URLEncode
 * @author dingyw
 *
 * 2017年3月22日
 */
public class SDKReqParser {
	
	String file_name;
	
	public SDKReqParser(){
		
	}
	public SDKReqParser(String file_name){
		this.file_name=file_name;
	}
	/**
	 * @param plain_txt
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getMap(String plain_txt) throws Exception{
		
		Map<String,String>  map=new HashMap<String,String>();
		
		//URLDECODE
		plain_txt=URLDecoder.decode(plain_txt, "utf-8");
		//去掉头的URL;
		plain_txt=plain_txt.subSequence(plain_txt.indexOf("?")+1, plain_txt.length()).toString();
		int a=plain_txt.indexOf("{");
		int b=plain_txt.indexOf("}}");
		String[] strs = plain_txt.split("&");
		for(String item:strs){
			String[] items = item.split("=");
			
			if("reqjson".equals(items[0])){
				String reqjson=plain_txt.substring(a, b+2);
				map.put(items[0], reqjson);
				continue;
			}
			if(this.count(item)>=2){ //如果有多个等号
				map.put(items[0], item.substring(item.indexOf("=")));
			}else{
				map.put(items[0], items[1]);
			}
			}
		return map;
	}
	
	//统计str中出现"="号的次数
	private int count(String s){
		int len1 = s.length();
		String s1 = s.replaceAll("=","");
		int len2 = s1.length();	
		return len1-len2;
	}
	/**获取请求日志的参数，放到map里
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getMap() throws Exception{
		String plain_txt=null;
		if(StringUtils.isEmpty(file_name)){
			plain_txt=FileUtils.getSdkPlainTxtEncode();
		}else{
			plain_txt=FileUtils.getSdkPlainTxtEncode(file_name);
		}
		Map<String,String> map=this.getMap(plain_txt);
		return map;
	}
	public static void main(String[] args) {
		SDKReqParser parser=new SDKReqParser();
		Map<String, String> map;
		try {
			map = parser.getMap();
			String json=map.get("reqjson");
			System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
