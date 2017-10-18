package com.wk.ssp.utils;

/**
 * 版本号工具类
 * @description 
 * @author wuxing
 * @date 2017年7月26日
 *
 */
public class SdkVersionUtil {
	
	/**
	 * 版本号比较方法
	 * 
	 * @author: wuxing
	 * @date: 2017年7月26日 下午2:57:08
	 *
	 */
	public static int compareVersion(String currentVersion, String compareToVersion){
		//获取不到版本号时, 当做旧版本处理
		if (null == currentVersion) {
			return -1;
		}

		String[] compareToVersionArray = compareToVersion.split("\\.");
		String[] currentVersionArray = currentVersion.split("\\.");
		int compareToVersionLength = compareToVersionArray.length;
		int currentVersionLength = currentVersionArray.length;
		int result = 0;

		int shortLength = Math.min(compareToVersionLength, currentVersionLength);

		for (int i = 0; i < shortLength; i++) {
			result = currentVersionArray[i].compareTo(compareToVersionArray[i]);
			if (result > 0) {
				return 1;
			} else if (result < 0) {
				return -1;
			}
		}

		if (compareToVersionLength == currentVersionLength) {
			return 0;
		}

		int longLength = Math.max(compareToVersionLength, currentVersionLength);

		for (int j = shortLength; j < longLength; j++) {
			if (currentVersionLength > compareToVersionLength) {
				if (currentVersionArray[j].compareTo("0") > 0) {
					return 1;
				} else if (currentVersionArray[j].compareTo("0") == 0) {
					continue;
				}
			} else {
				if (compareToVersionArray[j].compareTo("0") > 0) {
					return -1;
				} else if (compareToVersionArray[j].compareTo("0") == 0) {
					continue;
				}
			}
		}

		return 0;
	}
}
