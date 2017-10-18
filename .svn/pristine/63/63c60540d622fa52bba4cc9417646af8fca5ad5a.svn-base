package com.gionee.ssp.controller.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author dingyw
 *
 * 2017年10月12日
 */
public class BaseSDKCrashLogController {
	
	/**获取请求参数
     * @param request
     * @return
     * @throws IOException
     */
    protected String getJson(HttpServletRequest request) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        int lenth = inputStream.available();
        GZIPInputStream gzip = new GZIPInputStream(inputStream, inputStream.available());
        ByteArrayOutputStream out =  new ByteArrayOutputStream();
        byte[] buf = new byte[lenth];
        
        @SuppressWarnings("unused")
        int count;
        while((count = gzip.read(buf, 0, lenth)) != -1) {
            out.write(buf, 0, lenth);
        }
        
        byte[] data = out.toByteArray();
        try {
          out.close();
        } catch (IOException e) {
        }
        try {
            gzip.close();
        } catch (IOException e) {
        }
        try {
            inputStream.close();
        } catch (IOException e) {
        }
        String json = new String(data);
        json = json.replaceAll("\n\t", "%%"); // \n\t会导致json转化失败替换
        
        return json;
    }

}
