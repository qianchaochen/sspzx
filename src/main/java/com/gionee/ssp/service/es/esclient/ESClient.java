package com.gionee.ssp.service.es.esclient;

import java.net.InetAddress;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.wk.ssp.utils.WKObjectUtils;

/**
 *
 * 原生ES的Client
 *
 */
public class ESClient {

    /**
     * 实例
     */
    private TransportClient client;

    /**
     * 配置(通过xml配置文件配置)
     */
    private Map<String, String> settingsMap;
    
    /**(通过xml配置文件配置)
     * es.hostandports=192.168.17.128:9300|192.168.17.129:9300
     */
    private String esHostAndPorts;
    /**
     * @param settingsMap
     * @param esHostAndPorts
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        Settings settings = Settings.settingsBuilder().put(settingsMap).build();
        client = TransportClient.builder().settings(settings).build();
        if (StringUtils.isBlank(esHostAndPorts)) {
            throw new Exception("没有配置es.hostandports");
        }
        String[] hostAndPorts = StringUtils.split(esHostAndPorts, "\\|");
        for (String hostAndPort : hostAndPorts) {
            String[] config = StringUtils.split(hostAndPort, "\\:");
            String host = config[0];
            int port = WKObjectUtils.toInt(config[1]);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        }
    }

  
    /**
     * 销毁
     */
    @PreDestroy
    public void destroyClient() {
        if (client != null) {
            client.close();
            client = null;
        }
    }


    /**返回client
     * @return
     */
    public Client getClient() {
        return client;
    }

	public Map<String, String> getSettingsMap() {
		return settingsMap;
	}

	public void setSettingsMap(Map<String, String> settingsMap) {
		this.settingsMap = settingsMap;
	}

	public String getEsHostAndPorts() {
		return esHostAndPorts;
	}

	public void setEsHostAndPorts(String esHostAndPorts) {
		this.esHostAndPorts = esHostAndPorts;
	}
    
}
