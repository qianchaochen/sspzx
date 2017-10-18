package com.gionee.ssp.thread.connection;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * @description: 连接池清理线程
 */
public class IdleConnectionMonitorThread extends Thread {

	private final HttpClientConnectionManager connMgr;
	private final long cleanTime;
	private final int timeout;
	private volatile boolean shutdown;

	public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr, long cleanTime, int timeout) {
		super();
		this.connMgr = connMgr;
		this.cleanTime = cleanTime;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(cleanTime);
					// Close expired connections
					connMgr.closeExpiredConnections();
					// Optionally, close connections
					// that have been idle longer than 30 sec
					connMgr.closeIdleConnections(timeout, TimeUnit.SECONDS);
//					log.info("完成一次http连接池清理" + cleanTime);
				}
			}
		} catch (InterruptedException ex) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}
