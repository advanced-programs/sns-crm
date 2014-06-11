package zx.soft.sns.core.utils;

import java.security.MessageDigest;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * 校验工具类
 * @author wanggang
 *
 */
public class CheckSumUtils {

	/**
	 * CRC32校验
	 */
	public static long getCRC32(String str) {
		// get bytes from string
		byte bytes[] = str.getBytes();
		Checksum checksum = new CRC32();
		// update the current checksum with the specified array of bytes
		checksum.update(bytes, 0, bytes.length);
		// get the current checksum value
		return checksum.getValue();
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String getMD5(String str) {

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return null;
		}
		if (md5 == null) {
			return null;
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

}
