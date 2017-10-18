package com.gionee.ssp.listener;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.wk.ssp.utils.DataUtils;
import com.wk.ssp.utils.http.HttpPoolUtils;
import com.wk.ssp.utils.http.WKHttpUtils;


/**系统初始时，初始化一些参数和操作
 * @author dingyw
 *
 * 2017年9月15日
 */
@Component
public class DataInitListener implements ApplicationListener<ApplicationEvent> {
    private boolean closeFlag = false; // 服务关闭标识
    private boolean startFlag = false; // 服务启动标识
    
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
        if (event instanceof ContextRefreshedEvent && !startFlag) {
            closeFlag = false;
            startFlag = true;
			
            Properties properties = DataUtils.getConfigProperties(new ClassPathResource("const.properties"));

			//初始化Http连接池配置
			HttpPoolUtils.setDefaultMax(Integer.parseInt(properties.getProperty("DEFAULT_MAX")));
			HttpPoolUtils.setDefaultRout(Integer.parseInt(properties.getProperty("DEFAULT_ROUT")));
            try {
                //初始化http连接池
                WKHttpUtils.initClilent();                
            } catch (Exception e) {
                e.printStackTrace();
            }

		} else if(event instanceof ContextStartedEvent) {
		} else if(event instanceof ContextStoppedEvent) {
        } else if (event instanceof ContextClosedEvent && !closeFlag) {
            closeFlag = true;
            startFlag = false;
            try {
            	//关闭http连接池
                WKHttpUtils.closeClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
        
	}

}
