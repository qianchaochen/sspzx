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

/**模拟SDK请求百度
 * @author dingyw
 *
 * 2016年12月16日
 */
public class TestSdkReqBaidu {
	
	
	public static void main(String[] args) throws IOException {
		
		StringBuffer req_buf=new StringBuffer();
		TestSdkReqBaidu t=new TestSdkReqBaidu();
		
		//vo
		SdkRequestVO req=t.getReq();
		
		CloseableHttpClient httpclient=null;
		 CloseableHttpResponse  response=null;
		try {
			String json=JsonUtils.writeObject2Json(req);
			System.out.println(json);
			req_buf.append("?reqjson=");
			req_buf.append(URLEncoder.encode(json,"utf-8"));
			req_buf.append("&channel_id=3&device=bignox_VPhone&cuid=B2128E2413A11CD48B16D10570542168&client_id=AD5T82WWrQJrLUYLJkUzpw==&device_id=zO1F1v1+CC2Vv90TPmLxpgVWVR+XMd819EjXG0C/x+s=&os_level=19&sn=SSP_SDK&svr=1.4.8");
			
			httpclient = HttpClients.createDefault();    
            String uri =req_buf.toString();
            
            String url_enc="http://localhost:8080/ssppb/v1.1/getad"+uri;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{  
            httpclient.close();  
            response.close();  
        }  
		
	}
	
	
	private SdkRequestVO getReq(){
		SdkRequestVO req=new SdkRequestVO();
		req.setApi_version("1.1.0");
		
		SdkAppVo app=new SdkAppVo();
		app.setApp_id("1");
		app.setApp_version("1.4.7");
		app.setChannel_id("3");
		app.setPackage_name("com.ssp_sdk.demo");
		
		SdkAdslotVO adslot=new SdkAdslotVO();
		adslot.setAdslot_id("3");
		adslot.setAdslot_w(640);
		adslot.setAdslot_h(960);
		
		SdkDeviceVO device=new SdkDeviceVO();
		device.setDevice_type(4);
		device.setOs_type("Android");
		device.setOs_version("4.4.2");
		device.setVendor("GIONEE");
		device.setModel("V188");
		device.setAndroid_id("7d8d7727a86738ee");
		device.setImei_md5("03ABD9EA1E989F4788BC47D774B18F69");
		device.setImei_md5("865293020631078");
		device.setMac("b8:98:f7:aa:7d:f1");
		device.setW(640);
		device.setH(960);
	
		
		
		SdkNetworkVo network =new SdkNetworkVo();
		network.setConnect_type(6);
		network.setCarrier(1);
		network.setCellular_id("");
		network.setIp("210.75.18.197");
		
		SdkGpsVO gps=new SdkGpsVO();
		gps.setCoordinate_type(1);
		gps.setLon(114.031204);
		gps.setLat(22.543308);
		
		gps.setTimestamp(System.currentTimeMillis());
		
		
		req.setApp(app);
		req.setAdslot(adslot);
		req.setDevice(device);
		req.setNetwork(network);
		req.setGps(gps);
		
		return req;
	}

}
