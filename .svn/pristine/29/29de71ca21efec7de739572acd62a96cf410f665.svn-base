package com.gionee.ssp.service.gameAndStore.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gionee.ssp.conf.gameAndStore.GameAppSettings;
import com.gionee.ssp.service.gameAndStore.AppDetailSearchService;
import com.gionee.ssp.service.gameAndStore.GameAndAppstoreService;
import com.gionee.ssp.vo.gameAndStore.AppDetailQueryVo;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.AppDetailInfoVo;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @ClassName GameAndAppstoreServiceImpl
 * @Desc {获取应用商店和游戏大厅应用分发广告详细信息}
 * @author zhengk
 * @date Mar 10, 2017
 */
@Service
public class GameAndAppstoreServiceImpl implements GameAndAppstoreService {

	/**
	 * 应用详情服务层
	 */
	@Autowired
	AppDetailSearchService detailSearchService;
	
	/**
	 * 游戏大厅h5下载地址
	 */
	@Value("#{const_config.GAME_HALL_DOWNLOAD_H5_URL}")
	private String GAME_HALL_DOWNLOAD_H5_URL;
	
	
	/**
	 * 应用商店 h5下载地址
	 */
	@Value("#{const_config.APP_STORE_DOWNLOAD_H5_URL}")
	private String APP_STORE_DOWNLOAD_H5_URL;

	@Override
	public AppDetailInfoVo getDetail(AppDetailQueryVo queryVO) throws Exception {

		List<AppDetailInfoVo> rtn = detailSearchService.search(queryVO);

		return CollectionUtils.isEmpty(rtn) ? null: rtn.get(0);
	}
	/**
	 * 
	 * @Title: dealWithGameHallAndAppStoreAdInfo
	 * @Desc {游戏大厅和应用商店广告素材特殊处理}
	 * @param sdkResponseVO
	 * @param fillingDataVO
	 * @param requestVO
	 * @throws Exception
	 * @retrun void
	 * @author zhengk
	 * @date Apr 13, 2017 12:46:49 PM
	 */
	@Override
	public void dealWithGameHallAndAppStoreAdInfo(SdkResponseVO sdkResponseVO, FillingDataVO fillingDataVO) throws Exception {
		// FIXME 如果是应用商店和游戏大厅广告，在此添加查询ES
		boolean isAppStore = GameAppSettings.CHANNEL.APP_STORE.bundle.equals(fillingDataVO.getBundle());
		boolean isGameHall = GameAppSettings.CHANNEL.GAME_HALL.bundle.equals(fillingDataVO.getBundle());
		//error_code为零，并且请求的应用包名为应用商店或者游戏大厅
		//应用商店和游戏大厅数据处理
		if (sdkResponseVO.getError_code()==0){
			
			AppDetailQueryVo queryVo = new AppDetailQueryVo();
			String h5url="";
			if((isAppStore || isGameHall)){
				
				if(isAppStore){
					queryVo.setChannelId(GameAppSettings.CHANNEL.APP_STORE.code);
					h5url = APP_STORE_DOWNLOAD_H5_URL;
				}else if(isGameHall){
					queryVo.setChannelId(GameAppSettings.CHANNEL.GAME_HALL.code);
					h5url = GAME_HALL_DOWNLOAD_H5_URL;
				}
				
				int rtnAdCtn = sdkResponseVO.getAdms().size();
			
				for(int i=0;i<rtnAdCtn;i++){
					
					String adBundle = sdkResponseVO.getAdms().get(i).getBundle();
					
					if(StringUtils.isBlank(adBundle)){
						continue;
					}
					queryVo.setBundle(adBundle);
					
					AppDetailInfoVo detailInfo = this.getDetail(queryVo);
					if(detailInfo!=null){
						detailInfo.setH5Url(h5url);
						detailInfo.setAd_h5(null);
						detailInfo.setOpen_type(0);
					}
					sdkResponseVO.getAdms().get(i).setGame_store_extra(detailInfo);
				}
			}
			else{
				int rtnAdCtn = sdkResponseVO.getAdms().size();
				
				for(int i=0;i<rtnAdCtn;i++){
					
					//根据clkurl判断广告素材来至应用商店还是游戏大厅
					String cllUrl = sdkResponseVO.getAdms().get(i).getClkurl();
					
					if(cllUrl==null){
						continue;
					}
					
					//应用商店
					if(cllUrl.indexOf("com.gionee.aora.market.action.GoSoftIntroductionActivity")!= -1){
						queryVo.setChannelId(GameAppSettings.CHANNEL.APP_STORE.code);
						h5url = APP_STORE_DOWNLOAD_H5_URL;
					}
					//游戏大厅
					else if(cllUrl.indexOf("gn.com.android.gamehall.action.external.DETAIL")!= -1){
						queryVo.setChannelId(GameAppSettings.CHANNEL.GAME_HALL.code);
						h5url = GAME_HALL_DOWNLOAD_H5_URL;
					}else{
						//非应用商店和游戏大厅素材，不用获取extra，处理clkurl即可
						continue;
					}
					
					String adBundle = sdkResponseVO.getAdms().get(i).getBundle();
					
					if(StringUtils.isBlank(adBundle)){
						continue;
					}
					queryVo.setBundle(adBundle);
					
					AppDetailInfoVo detailInfo = this.getDetail(queryVo);
					if(detailInfo!=null){
						detailInfo.setH5Url(h5url);
						detailInfo.setOpen_type(0);
						//产品明确需求  104 将音乐的open_type 设为4
						if(fillingDataVO.getBundle().equals("com.gionee.amisystem.yourpage")){
							detailInfo.setOpen_type(1);
						}
//						else if(fillingDataVO.getBundle().equals("com.gionee.gsp")){
//							detailInfo.setOpen_type(3);
//						}
						detailInfo.setAd_h5(null);  //目前并没有该h5地址
					}
					sdkResponseVO.getAdms().get(i).setGame_store_extra(detailInfo);
				}
			}
		}
		//end 
	}
}