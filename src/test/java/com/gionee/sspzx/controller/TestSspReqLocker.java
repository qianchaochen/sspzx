package com.gionee.sspzx.controller;

import java.util.Map;

import com.gionee.sspzx.analyze.SspLogReqParser;
import com.wk.ssp.vo.sdk.SdkRequestVO;

public class TestSspReqLocker extends BaseTestSDKController{
	/**
	 * 锁屏请求日志
	 * [from:sdk][svr:1.7.6][device_n:GIONEE_GIONEE S10][cuid:4CC368E50461359DE494A0A7F12DCA50][client_id:kEK59QpNRFwwGGPxFz76HQ==][device_id:ydNw0HurzcMCgooLFJDDErtk/lCmfXyUJ16L+cj1dBE=][reqjson:{"api_version":"1.1.0","app":{"app_id":"2659","channel_id":"3","app_version":"1.7.6.a","package_name":"com.ssp_sdk.demo"},"device":{"device_type":4,"os_type":"Android","os_version":"7.0","vendor":"GIONEE","model":"GIONEE S10","android_id":"91eb3589f1a935ba","imei_md5":"5284047F4FFB4E04824A2FD1D1F0CD62","mac":"00:08:22:24:c0:fb","w":1080,"h":1920,"dpi":480,"ua":"Dalvik\/2.1.0 (Linux; U; Android 7.0; GIONEE S10 Build\/NRD90M)","web_ua":"Dalvik\/2.1.0 (Linux; U; Android 7.0; GIONEE S10 Build\/NRD90M)","por":0,"language":"zh","rp":"1080_1920","isroot":1,"btmac":"02:00:00:00:00:00","pdunid":"4HCQNNAYTKNVIRSC","cputy":"unknown","cpusn":"0000000000000000","imsi":"","app_list":""},"adslot":{"adslot_id":"3551","adslot_w":0,"adslot_h":0},"network":{"ip":"192.168.122.2","connect_type":2,"carrier":0,"cellular_id":"23257355","lac":"42289","mcc":"460","bss_id":"70:f9:6d:b0:02:71","netwk_id":"0","ssid":"\"gionee_staff\"","lksd":65,"rssi":-68,"roaming":0,"stbif":[]},"gps":{"coordinate_type":1,"lon":-1,"lat":-1,"timestamp":1493023686554}}][request_id:2a57945cee392a23cbe863f8eddf0ae5][req_ad_cnt:1][ipush:0][is_debug:0][imp_ids:][is_download:][is_error:0][time:1493023681987][process_time:5]
	 *
	 */
	public void execute() throws Exception {
		//是否采用本地环境还是测试环境
		this.setEnv("product");
		
		//构造一个parser,分析TestSspLogReq.txt日志
		SspLogReqParser parser=new SspLogReqParser();
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
		TestSspReqLocker t=new TestSspReqLocker();
		try {
			t.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
