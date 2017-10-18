package com.gionee.sspzx.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.vo.sdk.SdkAdslotVO;
import com.wk.ssp.vo.sdk.SdkAppVo;
import com.wk.ssp.vo.sdk.SdkDeviceVO;
import com.wk.ssp.vo.sdk.SdkGpsVO;
import com.wk.ssp.vo.sdk.SdkNetworkVo;
import com.wk.ssp.vo.sdk.SdkRequestVO;



/**自行构造SDK参数
 * @author dingyw
 *
 * 2017年3月22日
 */
public class TestSDKReq extends BaseTestSDKController{
	public void execute() throws IOException {
		//是否采用本地环境还是测试环境
		this.setEnv("product");
		String url=this.getUrl();
		
		StringBuffer req_buf=new StringBuffer();
		
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse  response=null;
		try {
			String json=JsonUtils.writeObject2Json(this.getReq());
			System.out.println(json);
			
			req_buf.append("?reqjson=");
			req_buf.append(URLEncoder.encode(json,"utf-8"));
			req_buf.append(URLEncoder.encode(this.getPublicParam(),"utf-8"));//SDK公参
			
			httpclient = HttpClients.createDefault();    
			
            String uri =req_buf.toString();
            
            String url_enc=url+uri;
            System.out.println(url_enc);
            HttpGet httpGet = new HttpGet(url_enc); 
            response = httpclient.execute(httpGet); 
            // 获取响应实体      
            HttpEntity entity = response.getEntity();  
            // 打印响应状态      
            System.out.println(response.getStatusLine().getStatusCode());    
            if (entity != null) {    
                // 打印响应内容      
                System.out.println("Response content: " + EntityUtils.toString(entity));    
            }  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  
            httpclient.close();  
            response.close();  
        }  
	}
	public static void main(String[] args) {
		TestSDKReq t=new TestSDKReq();
		try {
			t.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private SdkRequestVO getReq(){
		//TestSdkRequestVO去掉SdkRequestVO一个字段，生成的json就没有该字段把sdk公参的vo去掉
		SdkRequestVO req=new SdkRequestVO(); 
		req.setApi_version("1.1.0");
		
		//app信息
		SdkAppVo app=new SdkAppVo();
		app.setApp_id("2622");
		app.setApp_version("1.7.5.l");
		app.setChannel_id("3");
		app.setPackage_name("com.ssp_sdk.demo");
		
		//广告位信息
		SdkAdslotVO adslot=new SdkAdslotVO();
		adslot.setAdslot_id("3493");
		adslot.setAdslot_w(1080);
		adslot.setAdslot_h(1920);
		
		//设备信息
		SdkDeviceVO device=new SdkDeviceVO();
		device.setDevice_type(4);
		device.setOs_type("Android");
		device.setOs_version("7.0");
		device.setVendor("GIONEE");
		device.setModel("GIONEE S10B");
		device.setAndroid_id("b316129c39d6b11c");
		device.setImei_md5("B8F1ACA506F70B4599DA32155F86D209");
		device.setMac("b8:98:f7:a3:cf:1e");
		device.setW(1080);
		device.setH(1920);
		device.setDpi(480);
		device.setUa("");
		
		//网络信息
		SdkNetworkVo network =new SdkNetworkVo();
		network.setConnect_type(2);
		network.setCarrier(1);
		network.setCellular_id("46314753");
		network.setIp("192.168.1.108");
		
		//位置信息
		SdkGpsVO gps=new SdkGpsVO();
		gps.setCoordinate_type(1);
		gps.setLon(-1);
		gps.setLat(-1);
		gps.setTimestamp(System.currentTimeMillis());
		
		
		req.setApp(app);
		req.setAdslot(adslot);
		req.setDevice(device);
		req.setNetwork(network);
		req.setGps(gps);
		
		return req;
	}
	private String getPublicParam(){
		String svr = "1.7.1"; //SDK版本
		String device = "GIONEE_GIONEE S10B";
		String cuid = "FDFE41EE9189200DFD6A4F141C98C5B0";
		String clientId = "B/nuhKn+Rzw/nIourTNpxg==";
		String deviceId = "Wvxiqrca9jYlZezhEbreSGsOXw+tLrK/vuPw5Fqx9gk=";
		
		return 	  "&channel_id=3"
				+ "&device_n="+device
				+ "&cuid="+cuid
				+ "&client_id="+clientId
				+ "&device_id="+deviceId
				+ "&os_level=24"
				+ "&sn=SSP_SDK"
				+ "&svr="+svr;
	}

}
