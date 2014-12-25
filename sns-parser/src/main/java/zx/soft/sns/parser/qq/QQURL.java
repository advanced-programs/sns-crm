package zx.soft.sns.parser.qq;

import static zx.soft.sns.parser.constant.QQConstant.QQ;
import static zx.soft.sns.parser.constant.QQConstant.ROOT_URL;

public class QQURL {

	/**
	 * 获取查询QQ号或者QQ群号的URL
	 */
	public static String getQQUrl(long qq) {
		return ROOT_URL + QQ + "=" + qq;
	}

}
