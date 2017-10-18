package com.gionee.ssp.service.ipush.es.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.ipush.IpushConstant;
import com.gionee.ssp.service.es.esclient.ESClient;
import com.gionee.ssp.service.ipush.es.AdSearchService;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeMessageVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;
import com.wk.ssp.utils.DateTimeUtils;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.WKObjectUtils;

/**
 *
 * 广告搜索服务实现类
 *
 */
@Service
public class AdSearchServiceImpl implements AdSearchService {
	
	Logger logger = LogManager.getLogger(getClass());

	/**
	 * es client服务层
	 */
	@Autowired
	ESClient esClient;

	/**
	 * 根据条件查询相关信息
	 * 
	 * @param campaignVO
	 *            传入查询条件
	 * @return 返回查询结果
	 * @throws Exception
	 */
	@Override
	public List<CampaignVO> search(QueryVO queryVO) throws Exception {
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

		queryBuilder.must(QueryBuilders.termQuery("status", 1)); // 活动状态
																	// 1-暂停使用
																	// 0-正在使用
		queryBuilder.must(QueryBuilders.rangeQuery("start_time").lte(DateTimeUtils.getCurrentSecond())); // 大于等于活动开始日期
		queryBuilder.must(QueryBuilders.rangeQuery("end_time").gt(DateTimeUtils.getCurrentSecond())); // 小于活动结束日期
		queryBuilder.must(QueryBuilders.termQuery("adslots", queryVO.getAdslot())); // 广告位ID数组

		if (queryVO.getAd_type() != 0) {
			queryBuilder.must(QueryBuilders.termQuery("ad_type", queryVO.getAd_type())); // 创意形式：1：横幅，2：插屏,3:开屏,
																							// 4:原生广告
																							// 5横幅
		}

		queryBuilder.must(QueryBuilders.termQuery("advertising_times", DateTimeUtils.getTime()));// 投放时间。数组。星期前三个字母+小时数.例：["Mon_00","Tue_12"]

		if (!ObjectUtils.isEmpty(queryVO.getArea())) {
			queryBuilder.must(QueryBuilders.termsQuery("areas", new int[] { queryVO.getArea(), 0 }));// 投放区域。地区码数组。不限位000
		}
		queryBuilder.must(QueryBuilders.termsQuery("connection_type", new int[] { queryVO.getConnectionType(), 0 }));// 连接类型：0：全部，1：2G/3G/4G,2:wifi

		if (queryVO.getCarrier() != 0 && queryVO.getCarrier() != 4) {
			queryBuilder.must(QueryBuilders.termQuery("carriers", queryVO.getCarrier()));// 运营商类型数组：0：未知运营商，1：中国移动，2：中国电信，3：中国联通，4：其他运营商
		} else {
			queryBuilder.must(QueryBuilders.termQuery("carriers", 1));
			queryBuilder.must(QueryBuilders.termQuery("carriers", 2));
			queryBuilder.must(QueryBuilders.termQuery("carriers", 3));
		}

		String os = queryVO.getOs();
		if (!StringUtils.isBlank(queryVO.getOs())) {
			if (os.equals("android")) {
				queryBuilder.must(QueryBuilders.termQuery("os", 0));// 操作系统类型。0：android，1：ios
			} else if (os.equals("ios")) {
				queryBuilder.must(QueryBuilders.termQuery("os", 1));// 操作系统类型。0：android，1：ios
			}
		}

		if (!StringUtils.isBlank(queryVO.getOsv())) {

			int version = WKObjectUtils.formatString2Int4Version(queryVO.getOsv());
			queryBuilder.must(QueryBuilders.rangeQuery("osv.min_ver").lte(version)); // 最低版本号限制
			queryBuilder.must(QueryBuilders.rangeQuery("osv.max_ver").gte(version)); // 最高版本号限制
		}

		if (!StringUtils.isBlank(queryVO.getModel())) {
			queryBuilder.must(QueryBuilders.termsQuery("models", new String[] { "0", queryVO.getModel() })); // 机型
		}

		if (!ObjectUtils.isEmpty(queryVO.getBitc()) && queryVO.getBitc().get(0) != 0) {
			queryBuilder.must(QueryBuilders.termsQuery("campaign_target", queryVO.getBitc())); // 屏蔽动作类型
		}

		// 性别
		if (!StringUtils.isBlank(queryVO.getGender())) {
			queryBuilder.must(QueryBuilders.termsQuery("gender", new String[] { queryVO.getGender(), "0" })); // 性别
		} else {
			queryBuilder.must(QueryBuilders.termQuery("gender", 0)); // 性别
		}
		// 年龄
		if (!StringUtils.isEmpty(queryVO.getAge())) {
			queryBuilder.must(QueryBuilders.termsQuery("age", new String[] { queryVO.getAge(), "0" })); // 年龄
		} else {
			queryBuilder.must(QueryBuilders.termQuery("age", 0));
		}
		
		// 兴趣
		BoolQueryBuilder interestsAndAppQueryBuilder = new BoolQueryBuilder();
		if (!CollectionUtils.isEmpty(queryVO.getInterests())) {
			interestsAndAppQueryBuilder
					.should(QueryBuilders.termsQuery("interests", ArrayUtils.add(queryVO.getInterests().toArray(), "0"))); // DMP标签组
		} else {
			interestsAndAppQueryBuilder.should(QueryBuilders.termQuery("interests", 0)); // DMP标签组
		}

		// APP定向
		if (!CollectionUtils.isEmpty(queryVO.getApps())) {
			interestsAndAppQueryBuilder.should(QueryBuilders.termsQuery("apps", ArrayUtils.add(queryVO.getApps().toArray(), "0"))); // DMP标签组
		} else {
			interestsAndAppQueryBuilder.should(QueryBuilders.termQuery("apps", 0)); // DMP标签组
		}
		
		interestsAndAppQueryBuilder.minimumNumberShouldMatch(1);

		queryBuilder.must(interestsAndAppQueryBuilder);

		List<Integer> itemTypes = new ArrayList<Integer>();
		if (queryVO.getAd_type() == 4) {

			List<CreativeMessageVO> creativeMessageVOs = queryVO.getCreativeMessageVO();
			int size = creativeMessageVOs.size();

			for (int i = 0; i < size; i++) {
				CreativeMessageVO creativeMessageVO = creativeMessageVOs.get(i);
				if (!ObjectUtils.isEmpty(creativeMessageVO.getItem_types())) {
					itemTypes.addAll(creativeMessageVO.getItem_types());
				}

				BoolQueryBuilder creativesquery = QueryBuilders.boolQuery()
						.must(QueryBuilders.rangeQuery("creatives.title_len").lte(creativeMessageVO.getLen())) // 主标题长度
						.must(QueryBuilders.rangeQuery("creatives.sub_title_len").lte(creativeMessageVO.getSub_len())); // 子标题长度

				queryBuilder.should(QueryBuilders.nestedQuery("creatives", creativesquery)); // 原生广告查询
			}
			queryBuilder.minimumNumberShouldMatch(1);

		} else {

			if (!queryVO.getCreativeMessageVO().isEmpty()) {
				CreativeMessageVO creativeMessageVO = queryVO.getCreativeMessageVO().get(0);
				if (!ObjectUtils.isEmpty(creativeMessageVO.getItem_types())) {
					itemTypes.addAll(creativeMessageVO.getItem_types());
				}

				if (itemTypes.contains(4)) {
					queryBuilder.should(QueryBuilders.nestedQuery("creatives",
							QueryBuilders.boolQuery()
									.must(QueryBuilders.termQuery("creatives.w", creativeMessageVO.getW())) // 创意宽
									.must(QueryBuilders.termQuery("creatives.h", creativeMessageVO.getH())))); // 创意高
				}

				if (itemTypes.contains(5)) {
					queryBuilder.should(QueryBuilders.nestedQuery("creatives",
							QueryBuilders.boolQuery().must(QueryBuilders.termQuery("creatives.w", 240)) // 创意宽
									.must(QueryBuilders.termQuery("creatives.h", 240)))); // 创意高
				}

				queryBuilder.minimumNumberShouldMatch(1);

			}

		}
		if (!ObjectUtils.isEmpty(itemTypes)) {
			queryBuilder.must(QueryBuilders.termsQuery("item_type", itemTypes)); // 广告类型
		}

		Client client = esClient.getClient();
		
		logger.info(client.prepareSearch(IpushConstant.ES_INDEX)//
					.setTypes(IpushConstant.ES_TYPE)//
					.setPostFilter(queryBuilder));
		
		SearchResponse response = client.prepareSearch(IpushConstant.ES_INDEX)//
				.setTypes(IpushConstant.ES_TYPE)//
				.setPostFilter(queryBuilder)//
				.execute().actionGet();

		SearchHit[] searchHits = response.getHits().getHits();
		List<CampaignVO> result = new ArrayList<CampaignVO>();

		for (SearchHit searchHit : searchHits) {
			String source = searchHit.getSourceAsString();
			if (!StringUtils.isBlank(source)) {
				result.add(JsonUtils.readJson2Object(source, CampaignVO.class));
			}
		}
		return result;
	}

}
