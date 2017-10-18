package com.gionee.ssp.startup.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.gionee.ssp.interceptor.LocalInterceptor;
import com.gionee.ssp.interceptor.LogInterceptor;

/**该类由WebInitializer负责调起
 * @author dingyw
 *
 * 2017年9月5日
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.gionee.ssp.controller", useDefaultFilters = false, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class MvcConfig extends WebMvcConfigurationSupport {


	private LogInterceptor logInterceptor = LogInterceptor.instance();
	
	private LocalInterceptor localInterceptor = LocalInterceptor.instance();
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();

		mapping.setInterceptors(new Object[] { getLogInterceptor(), getLocalInterceptor() });
		return mapping;
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/debug/**").addResourceLocations(
				"/WEB-INF/debug/");
		registry.addResourceHandler("/JS_Ad/**").addResourceLocations(
				"/JS_Ad/");
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(getLogInterceptor()).addPathPatterns("/v1.0/getad");
		registry.addInterceptor(getLocalInterceptor()).addPathPatterns("/v1.0/getad");
	}

	public void setLogInterceptor(LogInterceptor logInterceptor) {
		this.logInterceptor = logInterceptor;
	}

	@Override
	protected void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new ProtobufHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	
	public LogInterceptor getLogInterceptor() {
		return logInterceptor;
	}
	
	public LocalInterceptor getLocalInterceptor(){
		return localInterceptor;
	}
}
