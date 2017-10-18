package com.wk.ssp.utils;

import java.util.Iterator;

public class StringUtils {
	
	public static final String UTF8 = "UTF-8";

	public static final String UTF16 = "UTF-16";

	public static final String GB2312 = "GB2312";

	public static final String GBK = "GBK";

	public static final String ISO8859_1 = "ISO-8859-1";

	public static final String ISO8859_2 = "ISO-8859-2";

	public static final String EMPTY = "";

	public static final int INDEX_NOT_FOUND = -1;
	
	
	/**
	 * @title: capitalize
	 * @description: 字符串首字母大写
	 * @param: str
	 * @return: String
	 * @throws:
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}
	
	/**
	 * @title: uncapitalize
	 * @description:字符串首字母小写
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}
	
	/**
	 * @title: isEmpty
	 * @description:判断字符串是否为null或空字符串
	 * @param: cs
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	
	/**
	 * @title: isEmpty
	 * @description:判断字符串是否为null或空字符串
	 * @param: cs
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isEmptyString(String s) {
		return s == null || s.length() == 0;
	}
	
	/**
	 * @title: isNotEmpty
	 * @description:判断 字符串是否不为null或空字符串
	 * @param:
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}
	
	
	/**
	 * @title: isNotBlank
	 * @description:判断字符串是否不为null或连续空格
	 * @param: 
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}
	
	/**
	 * @title: trim
	 * @description:去掉字符串两端空格，若字符串为null则返回null
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
	
	/**
	 * @title: trimToNull
	 * @description:去掉字符串两端空格，若原字符串为null则返回null；
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}
	
	/**
	 * @title: trimToEmpty
	 * @description:去掉字符串两端空格，若原字符窜为null则返回空字符串
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}
	
	/**
	 * @title: deleteWhitespace
	 * @description:删除字符串中所有空格字符（包括两端空字符）
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String deleteWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		int sz = str.length();
		char[] chs = new char[sz];
		int count = 0;
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				chs[count++] = str.charAt(i);
			}
		}
		if (count == sz) {
			return str;
		}
		return new String(chs, 0, count);
	}
	
	/**
	 * @title: equals
	 * @description:判断两个字符串是否相等
	 * @param:
	 * @return: boolean
	 * @throws:
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return cs1 == null ? cs2 == null : cs1.equals(cs2);
	}
	
	/**
	 * @title: substring
	 * @description: 字符串截取
	 * @param:字符串 str
	 * @param:截取起点。若为负值，表示从字符串尾第start个开始
	 * @param:截取终点。若为负值，表示截取到从字符串尾底end个
	 * @return: String
	 * @throws:
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
		
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}
		return str.substring(start, end);
	}
	
	/**
	 * @title: left
	 * @description:从字符串左侧开始截取指定长度的字符串
	 * @param: 字符串 str
	 * @param: 长度 len
	 * @return: String
	 * @throws:
	 */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}
	
	/**
	 * @title: right
	 * @description: 从字符串右侧开始截取指定长度的字符串
	 * @param:
	 * @return: String
	 * @throws:
	 */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * @title: substringBefore
	 * @description: 截取指定字符串之前的字符串
	 * @param str
	 * @param separator
	 *            null-->原样返回，空字符串-->返回空字符串
	 * @return: String
	 * @throws:
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}
	
	/**
	 * @title: substringAfter
	 * @description:截取指定字符串之后的字符串
	 * @param str
	 * @param separator
	 *            null-->原样返回，空字符串-->返回空字符串
	 * @return: String
	 * @throws:
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}
	
	/**
	 * 判断{@link CharSequence} 是否为{@code null} 或全为空字符
	 * 
	 * @param cs {@link CharSequence} 用于判断
	 * @return {@code true} 如果{@link CharSequence} {@code null} 或全为空字符
	 */
	public static boolean isBlank(final CharSequence cs) {
		int pos = 0;

		if (cs == null || (pos = cs.length()) == 0) {
			return true;
		}

		while (pos > 0) {
			if (Character.isWhitespace(cs.charAt(pos-- - 1)) == false) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * @Title: join
	 * @Description: 将一个数组按照指定的连接符拼接成一个字符串
	 * @param array
	 * @param separator
	 *            连接符
	 * @param startIndex
	 *            起始索引
	 * @param endIndex
	 *            结束索引
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		int noOfItems = (endIndex - startIndex);
		if (noOfItems <= 0) {
			return EMPTY;
		}

		StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * @Title: join
	 * @Description: 将一个数组按照指定的连接符拼接成一个字符串(重载)
	 * @param array
	 * @param separator
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * @Title: join
	 * @Description: 将集合按照指定的连接符拼接成一个字符串
	 * @param iterator
	 * @param separator
	 * @return
	 * @return String
	 * @throws
	 */
	public static String join(Iterator<?> iterator, String separator) {
		if (iterator == null) {
			return null;
		}
		if (!iterator.hasNext()) {
			return EMPTY;
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return first == null ? "" : first.toString();
		}

		StringBuilder buf = new StringBuilder(256);
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			buf.append(separator);
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	/**
	 * @Title: join
	 * @Description: 将集合按照指定的连接符拼接成一个字符串(重载)
	 * @param iterable
	 * @param separator
	 * @return
	 * @return String
	 * @throws
	 */
	public static String join(Iterable<?> iterable, String separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}
}
