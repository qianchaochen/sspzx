package com.gionee.ssp.service.init;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.gionee.ssp.common.ipush.IpushConstant;
import com.wk.ssp.mvc.Constant;

/**地域信息初始化
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class AreaInfoInit {
	
	String file_name="area.properties";
	
	@PostConstruct
	public void init() throws IOException{
        // 地域信息配置
        Properties areaProperties = new Properties();
        PropertiesLoaderUtils.fillProperties(areaProperties,
                new EncodedResource(new ClassPathResource(file_name), Constant.CHARSET));
        Set<Object> areaKey = areaProperties.keySet();
        Map<String, String> areaMap = new HashMap<String, String>();
        for (Object key : areaKey) {
            areaMap.put(Objects.toString(key), areaProperties.getProperty(Objects.toString(key)));
        }
        IpushConstant.AREA_MAP.putAll(areaMap);
	}

}
