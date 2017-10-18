package com.wk.ssp.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.wk.ssp.mvc.Constant;

/**
 * @description:数据初始化工具
 */
public class DataUtils {

	/**
	 * @title: sizeAdapterInit
	 * @description: 初始化屏幕与广告大小的适配器
	 * @param adapter
	 * @param properties
	 */
	public static void sizeAdapterInit(Map<String, String> map, Resource resources) {

		Properties properties = getConfigProperties(resources);

		Map<String, String> data = new HashMap<String, String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.getProperty((String) key);
			String[] sizes = value.split(",");
            String[] size = null;
			for (int i = 0; i < sizes.length; i++) {
				data.put(sizes[i], (String) key);
                if (!Constant.ADAPTER_DEFULE.equals(sizes[i])) {
                    size = sizes[i].split("\\*");
                    data.put(size[1] + "*" + size[0], (String) key);
                }
			}
		}

		map.putAll(data);
	}

	/**
	 * @title: initMapFromProperties
	 * @description: 将properties中的数据初始化成map
	 * @param map
	 * @param properties
	 */
	public static void initMapFromProperties(Map<String, String> map, Resource resources) {

		Properties properties = getConfigProperties(resources);

		Map<String, String> data = new HashMap<String, String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.getProperty((String) key);
			data.put((String)key, value);
		}

		map.putAll(data);
	}

	/**
	 * @title: DataAdapter
	 * @description: 广告宽高适配
	 * @param adapter
	 * @param w
	 * @param h
	 * @return
	 */
	public static Map<String, Integer> DataAdapter(Map<String, String> adapter, int w, int h) {

		Map<String, Integer> adslotWH = new HashMap<String, Integer>(); // 适配后的广告位的宽高
		String condition = w + "*" + h; // 屏幕宽高
		String result = adapter.get(condition); // 适配后的结果

		// 若没有适配结果，使用默认配置
		if (StringUtils.isBlank(result)) {
			result = adapter.get(Constant.ADAPTER_DEFULE);
		}

		String[] wh = result.split("\\*"); // 拆分宽高
		// 填充宽高信息
		adslotWH.put(Constant.ADSLOT_WIDTH, Integer.valueOf(wh[0]));
		adslotWH.put(Constant.ADSLOT_HIGH, Integer.valueOf(wh[1]));

		return adslotWH;
	}

	/**
	 * @title: getConfigProperties
	 * @description: 读取配置文件
	 * @param resources
	 * @return
	 */
	public static Properties getConfigProperties(Resource... resources) {
		Properties properties = new Properties();
		for (Resource resource : resources) {
			try {
				PropertiesLoaderUtils.fillProperties(properties, new EncodedResource(resource, Constant.CHARSET));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return properties;
	}

}
