package com.gionee.ssp.service.init;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.gionee.ssp.utils.ip.IPUtils;

/**IP信息初始化
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class IpInfoInit {
	
	@PostConstruct
	public void init() throws IOException{
	    // IP地址库
	    File file = ResourceUtils.getFile("classpath:ipdb.dat");
	    IPUtils.load(file.getPath());	
	}

}
