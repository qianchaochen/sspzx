package com.gionee.ssp.utils.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**计算图片Md5的工具类
 * @author dingyw
 *
 * 2017年9月5日
 */
public class ImageMd5Utils {
	
	private static Logger log = LoggerFactory.getLogger(ImageMd5Utils.class);
	
	/**根据url计算图片的md5
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getImageMd5(String url) throws Exception {
		InputStream in = ImageMd5Utils.readImageUrl(url);
		if (in == null) {
			log.error("图片下载失败");
			throw new Exception("图片下载失败");
		}
		String str = null;
		try {
			str = ImageMd5Utils.getImageMd5(in);
		} catch (Throwable e) {
			log.error(e.getMessage(), "FileMD5Task fileNameMD5  error");
		}
		return str;
	}
	public static String getImageMd5(InputStream in) throws IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;

		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream

			digestInputStream = new DigestInputStream(in, messageDigest);

			// read的过程中进行MD5处理，直到读完文件

			byte[] buffer = new byte[bufferSize];

			while (digestInputStream.read(buffer) > 0)
				;
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();

			// 拿到结果，也是字节数组，包含16个元素

			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串

			return ImageMd5Utils.byteArrayToHex(resultByteArray);
		} catch (Throwable e) {
			log.error(e.getMessage(), "FileMD5Task fileMD5  error");
			return null;

		} finally {

			try {

				digestInputStream.close();

			} catch (Throwable e) {
				log.error(e.getMessage(), "FileMD5Task fileMD5 digestInputStream.close() error");
			}

		}

	}

	public static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

		char[] resultCharArray = new char[byteArray.length * 2];

		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

		int index = 0;

		for (byte b : byteArray) {

			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];

			resultCharArray[index++] = hexDigits[b & 0xf];
		}

		// 字符数组组合成字符串返回

		return new String(resultCharArray);

	}

	/**将URL转为inputStream
	 * @param url
	 * @return
	 */
	public static InputStream readImageUrl(String url) {
		byte[] btImg = ImageMd5Utils.getImageFromNetByUrl(url);
		if (btImg == null || btImg.length == 0) {
			return null;
		}

		return new ByteArrayInputStream(btImg, 0, btImg.length);
	}

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = ImageMd5Utils.readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Throwable e) {
			log.error(e.getMessage(), "FileMD5Task getImageFromNetByUrl error");
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
