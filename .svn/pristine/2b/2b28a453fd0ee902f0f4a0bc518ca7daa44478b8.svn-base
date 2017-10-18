package com.gionee.ssp.utils.baidu;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dingyw 序列号组成规则：序列号类型XXX(3位)+17位时间(yyyyMMddHHmmss)+4位（随机递增数,从0001到9999）+3位主机IP第三段+5位随机数 序列号类型：
 *         WAL：钱包类（默认） PUT：葡萄类
 * 
 * 
 *         2015年2月9日
 */
public class SeqIdSerial {

	Logger log = LoggerFactory.getLogger(getClass());
	// 最大数字
	private static final int MAX = 9999;
	// 数字长度
	private static final int NUMLEN = 4;

	private static SeqIdSerial instance = null;
	private String lastTime = "";
	private int lastNo = 0;
	private int baseNum = 10000;
	private static final int seqLen = 3;// 3位IP长度

	public SeqIdSerial() {

	}

	private static String getSerialStringByNum(int base, int value) {
		String num = new Integer(base + value).toString();

		return num.substring(1);
	}

	/**
	 * 根据当前时间产生一个新的序列号
	 * 
	 * @param sourceId
	 * @return
	 */
	public static synchronized String genRandomSeqId() {
		Date curTime = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 到毫秒

		if (instance == null) {
			instance = new SeqIdSerial();
			instance.lastNo = 0;
			instance.baseNum = (int) Math.pow(10, NUMLEN);
			instance.lastTime = s.format(curTime);
		}
		String now = s.format(curTime);

		if (now.compareTo(instance.lastTime) > 0) {
			// 当前时间大于上一次记录时间，表示可以开始新的计数
			instance.lastNo = 1;
			instance.lastTime = now;
			return now;
		}

		if (now.compareTo(instance.lastTime) < 0) {
			// 当前时间小于上一次的记录时间，表示上一秒有超过10000个流水号生成
			now = instance.lastTime;
		}

		int serialNo = instance.lastNo + 1;
		if (serialNo <= MAX) {
			// 当前没有超过最大允许流水号，返回上一个流水号+1作为新的流水号
			instance.lastNo = serialNo;
			return now;
		}

		try {
			Date last = s.parse(now);
			Calendar cal = Calendar.getInstance();
			cal.setTime(last);
			cal.add(Calendar.SECOND, 1);
			Date endTime = cal.getTime();
			String endStr = s.format(endTime);
			instance.lastNo = 1;
			instance.lastTime = endStr;
			return endStr;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}

	public static String genSeqId(String seqType) { // 指定三个参数时的流水号

		String now = genRandomSeqId();// 生成17位时间，并设置SeqIdSerial的baseNum、lastNo，提供生成随机数的条件
		String hostIP = getHostIP4Part();
		int len = seqLen - hostIP.length(); // 如果ip第三位不够3位长度，则生成随机数字填补
		hostIP = hostIP + generateRandomString(len);
		String str = seqType + now + getSerialStringByNum(instance.baseNum, instance.lastNo) + hostIP
				+ RandomStringUtils.randomAlphanumeric(5);// 拼接流水号
		return str;
	}


	/**
	 * 最常用的方法
	 * 
	 * @return
	 */
	public static String genSeqId() {// 参数为空时的默认流水号
		return genSeqId("com");
	}

	public static String generateRandomString(int len) {
		final char[] mm = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer sb = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < len; i++) {
			sb.append(mm[random.nextInt(mm.length)]);
		}
		return sb.toString();

	}

	public static void main(String[] args) {

		for (int i = 0; i < 9999; i++) {
			/* System.out.println("Get SeqId=" + genSeqId()); */// 第一种调用方法
			/* System.out.println("Get SeqId=" + genSeqId(CommonConstant.SEQ_TYPE.PUTAO.getValue())); */// 第二种调用方法
		}
	}

	/**
	 * 如果是分布式部署应用，生成的流水号加入IP的第三个字段，防止流水号冲突
	 * 
	 * @return
	 */
	public static String getHostIP4Part() {
		String hostIP4Part = null;
		try {
			String addr = InetAddress.getLocalHost().getHostAddress();

			if (addr == null) {
				hostIP4Part = "1.0.0.0";
			} else {
				hostIP4Part = addr.split("\\.")[3];
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hostIP4Part;
	}
}
