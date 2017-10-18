package com.gionee.sspzx.controller;

import java.util.Map;

import com.gionee.sspzx.analyze.SDKReqParser;
import com.wk.ssp.vo.sdk.SdkRequestVO;



/**
 *  * 通过抓取分析SDK的请求广告日志信息，重新拼装请求参数
 * 分析TestSdkReqEncode.txt来源于SDK发出的日志，用于重现请求广告的场景，或分析请求没有广告的场景
 * @author dingyw
 *j
 * 2017年3月22日
 */
public class TestReqBySDKParser extends BaseTestSDKController{
	public void execute() throws Exception {
		//是否采用本地环境还是测试环境
		this.setEnv("local");
		
		//构造一个parser,分析TestSdkReqEncode.txt日志
		SDKReqParser parser=new SDKReqParser();
		Map<String,String> map=parser.getMap();//map中存放各种参数
		
		String uri=this.getUri(map); //拼接请求参数
		
		//打印请求的vo日志
		SdkRequestVO vo=this.printRequestVo(map);
		
		//打印redis信息
		this.printRedisInfo(vo);
		
		//请求服务器
		this.httpGet(uri);
	}
	public static void main(String[] args) {
		TestReqBySDKParser t=new TestReqBySDKParser();
		try {
			t.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
