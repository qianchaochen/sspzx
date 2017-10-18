package com.gionee.sspzx.analyze;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gionee.common.FileUtils;

/**读取并且分析ssp的请求日志TestReq.txt的内容，然后构造TestSdkRequestVO
 * @author dingyw
 *
 * 2017年3月22日
 */
public class SspLogReqParser {
	
	/**
	 * 日志文件名
	 */
	private String fileName;
	
	public SspLogReqParser(){
		
	}
	public SspLogReqParser(String fileName){
		this.fileName=fileName;
	}
	
	/**获取请求日志的参数，放到map里
	 * @param plain_txt
	 * @return
	 */
	public Map<String,String> getMap(String plain_txt){
		
		Map<String,String>  map=new HashMap<String,String>();
		
		//去掉头和尾的[];
		plain_txt=plain_txt.subSequence(1, plain_txt.length()-1).toString();
		plain_txt=plain_txt.replace("][", "|");
		int a=plain_txt.indexOf("{");
		int b=plain_txt.indexOf("}|");
		String[] strs = plain_txt.split("\\|");
		for(String item:strs){
			String[] items = item.split(":");
			
			if("reqjson".equals(items[0])){
				map.put(items[0], plain_txt.substring(a, b+1));
				continue;
			}
			if(items.length>=2){
				map.put(items[0], items[1]);
				}
			}
		return map;
	}
	
	/**获取请求日志的参数，放到map里
	 * @return
	 */
	public Map<String,String> getMap(){
		String plain_txt=null;
		if(StringUtils.isEmpty(fileName)){
			 plain_txt=FileUtils.getSspPlainTxt();
		}else{
			 plain_txt=FileUtils.getSspPlainTxt(fileName);
		}
		Map<String,String> map=this.getMap(plain_txt);
		return map;
	}
	public static void main(String[] args) {
		SspLogReqParser parser=new SspLogReqParser();
		Map<String,String> map=parser.getMap();
		String json=map.get("reqjson");
		System.out.println(json);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
