package com.gionee.ssp.listener;

import java.util.Timer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import com.gionee.ssp.thread.anti.IpMapAccessResetThread;
import com.gionee.ssp.thread.anti.UpdateBlackListThread;
import com.wk.ssp.mvc.Constant;


/**系统初始时，启动线程
 * @author dingyw
 *
 * 2017年9月15日
 */
@Component
public class ThreadInitListener implements ApplicationListener<ApplicationEvent> {
    //定时器
	private Timer timer = new Timer();
    private boolean closeFlag = false; // 服务关闭标识
    private boolean startFlag = false; // 服务启动标识
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
        if (event instanceof ContextRefreshedEvent && !startFlag) {
            try {
                timer.schedule(new IpMapAccessResetThread(), 2000, Constant.ANTI_TIMER);
                timer.schedule(new UpdateBlackListThread(), 10, Constant.ANTI_BLACKLIST_SCAN_INTERVAL);              
            } catch (Exception e) {
                e.printStackTrace();
            }
		} else if(event instanceof ContextStartedEvent) {
		} else if(event instanceof ContextStoppedEvent) {
        } else if (event instanceof ContextClosedEvent && !closeFlag) {
            closeFlag = true;
            startFlag = false;
            timer.cancel();
		}   
	}
}
