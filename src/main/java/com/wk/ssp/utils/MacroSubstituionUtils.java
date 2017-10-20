package com.wk.ssp.utils;

import com.gionee.ssp.vo.IpushSource;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 *
 * 宏替换工具类
 *
 */
public class MacroSubstituionUtils {

    /**
     * 对第三方检测的监播进行替换，对于没有的信息可以不进行替换
     * @wuxing 2017-10-19 16:57
     * 根据ipushSource判断来源, 不同的来源进行不同的宏替换
     * @param url 第三方检测地址
     * @param queryVO 宏替换信息
     * @return 返回替换后的第三方检测地址
     * @throws Exception 
     */
    public static String macroSubstitutionMMA(final String url, final SdkRequestVO req, final QueryVO queryVO, final int ipushSource) throws Exception {
    	if(IpushSource.GIONEE.getSource() == ipushSource){
    		return macroSubstitutionGionee(url, queryVO);
    	}else if(IpushSource.ALI.getSource() == ipushSource){
    		return macroSubstitutionAli(url, req, queryVO);
    	}else{
    		//如果没有来源的字段, 默认用金立的宏替换
    		return macroSubstitutionGionee(url, queryVO);
    	}
    }
    
    /**
     * 金立直投广告宏替换
     * 
     * @author: wuxing
     * @date: 2017年10月19日 下午5:25:10
     *
     */
    private static String macroSubstitutionGionee(final String url, final QueryVO queryVO){
        String result = url;

        int os = 3;
        if (queryVO.getOs().toLowerCase().equals("android")) {
            os = 0;
        } else if (queryVO.getOs().toLowerCase().equals("ios")) {
            os = 1;
        } else if (queryVO.getOs().toLowerCase().equals("Windows Phone")) {
            os = 2;
        }
        result = result.replaceAll("__OS__", os + "") // 系统版本
                .replaceAll("__IMEI__", queryVO.getImeiMd5().toLowerCase()) // imei取MD5值
                .replaceAll("__IP__", queryVO.getIp()) // 客户端IP地址
                .replaceAll("__ANDROIDID__", queryVO.getDpidMd5().toLowerCase()); // android去MD5摘要
        return result;
    }
    
    
    /**
     * 阿里直投广告宏替换
     * 
     * @author: wuxing
     * @throws Exception 
     * @date: 2017年10月19日 下午5:24:57
     */
    private static String macroSubstitutionAli(final String url, final SdkRequestVO req, final QueryVO queryVO) throws Exception{
        String result = url;
        String mac = req.getDevice().getMac();
        if(StringUtils.isNotEmpty(mac)){
        	mac = MD5Utils.md5AsString(mac.replaceAll(":", "").toUpperCase());
        }
        result = result.replaceAll("__IMEI__", queryVO.getImeiMd5().toLowerCase()) // imei取MD5值
                .replaceAll("__MAC__", mac); // 
        return result;
    }
}
