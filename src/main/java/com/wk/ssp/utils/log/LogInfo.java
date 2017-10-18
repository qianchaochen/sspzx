package com.wk.ssp.utils.log;

import com.wk.ssp.utils.DateTimeUtils;

/**
 * 日志信息存储类
 *
 */
public abstract class LogInfo {

    final StringBuilder reqAdLogInfo = new StringBuilder();
    final StringBuilder sysLogInfo = new StringBuilder("ALERT:");
    String ip = "";
    String uri = "";
    long startTime = 0;

    /**
     * 构造方法，必须传入ip，如果没有ip请传入"null"
     * 
     * @param ip 日志的ip
     */
    LogInfo(final String ip, final String uri) {
        this.ip = ip;
        this.uri = uri;
        this.startTime = DateTimeUtils.getCurrentMillis();
    }

    /**
     * 获取{@code ip}
     * 
     * @return 返回{@code ip}
     */
    public abstract String getIp();
    
    /**
     * 获取{@code ip}
     * 
     * @return 返回{@code ip}
     */
    public abstract void setIp(String ip);
    
    /**
     * 设置向日志中添加请求的id
     * 
     * @param requestId 请求的id
     */
    public abstract LogInfo addrequestId(String requestId, LogLevel level);


    /**
     * 增加日志信息，按照key-value形式
     * 
     * @param key 主键
     * @param value 值
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addReqAdLog(final String key, final String value);

    /**
     * 在日志中添加错误信息(系统异常)
     * 
     * @param e 错误信息{code Exception}实例
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addSysErrorLog(final Exception e);

    /**
     * 在日志中添加错误信息（业务异常）
     * 
     * @param e 错误信息
     * @return 返回{@code LogInfo}实例
     */
    public abstract LogInfo addErrorLog(final String e);
    
    /**
     * @title: addNoContent
     * @description: 添加无内容返回标记
     * @return
     */
    public abstract LogInfo addNoContent();

    /**
     * 获取广告请求的log
     */
    public abstract String getReqAdLog();

    /**
     * 系统错误的toString 方法
     */
    public abstract String getSysErrorLog();
    
    /**
     * 是否是请求广告日志，如果不是，则不会把日志打印在请求的日志文件中
     */
    public abstract boolean isReqAdLog(); 
  
}
