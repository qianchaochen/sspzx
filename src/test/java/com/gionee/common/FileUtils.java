package com.gionee.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtils {
	/**文件读取Ssp请求日志
	 * @return
	 */
	public static String getSspPlainTxt(){
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\gionee\\common\\file\\TestSspLogReq.txt"; 
		return readTxtFile(filePath);
	}
	/**文件读取Ssp请求日志
	 * @return
	 */
	public static String getSspPlainTxt(String file_name){
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\gionee\\common\\file\\"+file_name; 
		return readTxtFile(filePath);
	}
	/**文件读取Dsp请求日志
	 * @return
	 */
	public static String getDspPlainTxt(){
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\gionee\\common\\file\\TestDspLogReq.txt"; 
		return readTxtFile(filePath);
	}
	/**文件读取SDK请求日志
	 * @return
	 */
	public static String getSdkPlainTxtEncode(){
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\gionee\\common\\file\\TestSdkReqEncode.txt"; 
		return readTxtFile(filePath);
	}
	/**文件读取SDK请求日志
	 * @return
	 */
	public static String getSdkPlainTxtEncode(String file_name){
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\gionee\\common\\file\\"+file_name; 
		return readTxtFile(filePath);
	}
	public static String readTxtFile(String filePath){
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        return lineTxt;
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     return null;
    }
}
