package com.gionee.ssp.service.gameAndStore.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.conf.gameAndStore.GameAppSettings;
import com.gionee.ssp.service.es.esclient.ESClient;
import com.gionee.ssp.service.gameAndStore.AppDetailSearchService;
import com.gionee.ssp.vo.gameAndStore.AppDetailQueryVo;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.vo.sdk.AppDetailInfoVo;

/**
 * 
 * @ClassName AppDetailSearchServiceImpl
 * @Desc {应用商店、游戏大厅应用详情es检索服务}
 * @author zhengk
 * @date Mar 13, 2017
 */
@Service
public class AppDetailSearchServiceImpl implements AppDetailSearchService {
	
	Logger logger = LogManager.getLogger(getClass());
	
	/**
	 * es client服务层
	 */
	@Autowired
	ESClient esClient;

	@Override
	public List<AppDetailInfoVo> search(AppDetailQueryVo queryVO) throws Exception {
		String type;
		String index;

		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

		queryBuilder.must(QueryBuilders.termQuery("bundle", queryVO.getBundle())); // 包名

		Client client = esClient.getClient();

		switch (GameAppSettings.CHANNEL.valueOf(queryVO.getChannelId())) {
		case APP_STORE:
			index = GameAppSettings.ES.APP_STORE.index;
			type = GameAppSettings.ES.APP_STORE.type;
			break;
		case GAME_HALL:
			index = GameAppSettings.ES.GAME_HALL.index;
			type = GameAppSettings.ES.GAME_HALL.type;
			break;
		default:
			index = GameAppSettings.ES.GAME_HALL.index;
			type = GameAppSettings.ES.GAME_HALL.type;
			break;
		}

		logger.info(client.prepareSearch(index)//
					.setTypes(type)//
					.setPostFilter(queryBuilder));
		
		SearchResponse response = client.prepareSearch(index)//
				.setTypes(type)//
				.setPostFilter(queryBuilder)//
				.execute().actionGet();

		SearchHit[] searchHits = response.getHits().getHits();
		List<AppDetailInfoVo> result = new ArrayList<AppDetailInfoVo>();

		for (SearchHit searchHit : searchHits) {
			String source = searchHit.getSourceAsString();
			if (!StringUtils.isBlank(source)) {
				result.add(JsonUtils.readJson2Object(source, AppDetailInfoVo.class));
			}
		}
		return result;
	}

}
