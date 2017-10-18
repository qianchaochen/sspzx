package com.wk.ssp.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import com.wk.ssp.mvc.Constant;

/**
 * MD5加密工具类
 *
 */
public final class MD5Utils {
	
	/**
	 * MD5 加密
	 * @param content 需要加密的数据是{@link String}类型的字符串
	 * @return 返回{@link String}类型的字符串
	 * @throws Exception 抛出异常
	 */
	public static String md5AsString(String content) throws Exception {
		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		return Hex.encodeHexString(mDigest.digest(content.getBytes(Constant.CHARSET))); 
	}
	
	/**
	 * MD5 加密
	 * @param content 需要加密的数据是{@link byte}类型数组
	 * @return 返回{@link String}类型的字符串
	 */
	public static String md5AsString(byte[] content) throws Exception {
		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		return Hex.encodeHexString(mDigest.digest(content));
	}
	
	/**
	 * MD5 加密
	 * @param content 需要加密的数据是{@link String}类型的数据
	 * @return 返回{@link byte}类型的数组
	 * @throws Exception 抛出异常
	 */
	public static byte[] md5(String content) throws Exception {
		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		return mDigest.digest(content.getBytes(Constant.CHARSET)); 
	}
	
	/**
	 * MD5 加密
	 * @param content 需要加密的数据是{@link byte}类型数组
	 * @return 返回{@link byte}类型的数组
	 */
	public static byte[] md5(byte[] content) throws Exception {
		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		return mDigest.digest(content); 
	}
	
}
