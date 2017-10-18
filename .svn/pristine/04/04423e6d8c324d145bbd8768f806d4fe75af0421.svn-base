package com.gionee.ssp.service.tracker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.tracker.InsertTrackerService;
import com.gionee.ssp.service.wk.impl.BaseTrackerServiceImpl;
import com.wk.ssp.utils.GeneratorUtils;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
@Service
public class InsertTrackerServiceImpl extends BaseTrackerServiceImpl implements InsertTrackerService{
	
	 /**
     * @title: insertImp
     * @description: 添加展示监播
     * @param req
     * @param vo
     */
	public void insertImp(SdkRequestVO req, SdkResponseAdVO vo, String requestId, String impId) {
        List<String> imps = vo.getImptrackers();
        // 如果adx响应中没有监播地址，新建
        if (imps == null) {
        	imps = new ArrayList<String>();
            vo.setImptrackers(imps);
        }
        imps.add(GeneratorUtils.generateDetecting(imp_tracker_url, req, requestId, "&imp_id=" + impId, "&source=" + vo.getAd_cp()));
    }

    /**
     * @title: insertClick
     * @description: 添加点击监播
     * @param req
     * @param vo
     */
    public void insertClick(SdkRequestVO req, SdkResponseAdVO vo, String requestId, String impId) {
        List<String> clicks = vo.getClktrackers();
        // 如果adx响应中没有监播地址，新建
        if (null == clicks) {
            clicks = new ArrayList<String>();
            vo.setClktrackers(clicks);
        }
        clicks.add(GeneratorUtils.generateDetecting(click_tracker_url, req, requestId, "&imp_id=" + impId));
    }

    /**
     * @title: insertDownload
     * @description: 添加下载监播
     * @param req
     * @param vo
     * @param requestId
     */
    public void insertDownload(SdkRequestVO req, SdkResponseAdVO vo, String requestId, String impId) {
    	//下载开始
         List<String> downloadStarts = vo.getDwnlst();
         // 如果adx响应中没有监播地址，新建
         if (null == downloadStarts) {
        	 downloadStarts = new ArrayList<String>();
             vo.setDwnlst(downloadStarts);
         }
         downloadStarts.add(GeneratorUtils.generateDetecting(download_start_tracker_url, req, requestId, "&imp_id=" + impId));
    	
    	
    	//下载结束
        List<String> downloads = vo.getDwnltrackers();
        // 如果adx响应中没有监播地址，新建
        if (null == downloads) {
            downloads = new ArrayList<String>();
            vo.setDwnltrackers(downloads);
        }
        downloads.add(GeneratorUtils.generateDetecting(download_finished_tracker_url, req, requestId, "&imp_id=" + impId));
    }

    /**
     * @title: insertInstall
     * @description: 添加安装监播
     * @param req
     * @param vo
     * @param requestId
     */
    public void insertInstall(SdkRequestVO req, SdkResponseAdVO vo, String requestId, String impId) {

        List<String> installs = vo.getIntltrackers();
        // 如果adx响应中没有监播地址，新建
        if (null == installs) {
            installs = new ArrayList<String>();
            vo.setIntltrackers(installs);
        }
        installs.add(GeneratorUtils.generateDetecting(install_finished_tracker_url, req, requestId, "&imp_id=" + impId));
    }

    /**
     * @title: insertActive
     * @description: 添加激活监播
     * @param req
     * @param ad
     * @param requestId
     */
    public void insertActive(SdkRequestVO req, SdkResponseAdVO ad, String requestId, String impId) {

        List<String> actives = ad.getActvtrackers();
        // 如果adx响应中没有监播地址，新建
        if (null == actives) {
            actives = new ArrayList<String>();
            ad.setActvtrackers(actives);
        }
        actives.add(GeneratorUtils.generateDetecting(active_tracker_url, req, requestId, "&imp_id=" + impId));
    }

}
