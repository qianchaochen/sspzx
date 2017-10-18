package com.gionee.ssp.startup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

/**该类由WebInitializer负责调起
 * @author dingyw
 *
 * 2017年9月5日
 */
@Configuration
@ComponentScan(basePackages = "com.gionee.ssp", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {
	
	//该类不需要被删除，主要功能是读取配置，初始化beans
}
