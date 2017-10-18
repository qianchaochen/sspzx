package com.gionee.ssp.startup.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.wk.ssp.mvc.Constant;

/**请注释掉web.xml配置后使用
 * 无web.xml的spring使用方式
 * @author dingyw
 *
 * 2017年9月5日
 */
@Order(1)
public class WebInitializer implements WebApplicationInitializer {

	@Override
    public void onStartup(javax.servlet.ServletContext sc) throws ServletException {

		//加载AppConfig
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
        rootContext.setServletContext(sc);
        rootContext.refresh();
		sc.addListener(new ContextLoaderListener(rootContext));
		
		// 1、springmvc上下文
		AnnotationConfigWebApplicationContext springMvcContext = new AnnotationConfigWebApplicationContext();
		springMvcContext.register(MvcConfig.class);

		// 2、DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcContext);
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/");

		// 3、CharacterEncodingFilter
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(Constant.CHARSET);
        FilterRegistration filterRegistration = sc.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");

	}
}
