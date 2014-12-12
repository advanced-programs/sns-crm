package zx.soft.sns.parser.wechat;

import static zx.soft.sns.parser.constant.WeChatConstant.OPEN_ID;
import static zx.soft.sns.parser.constant.WeChatConstant.OTHER_PARAMS;
import static zx.soft.sns.parser.constant.WeChatConstant.PAGE;
import static zx.soft.sns.parser.constant.WeChatConstant.PAGE_PUBLIC_ACCOUNT;
import static zx.soft.sns.parser.constant.WeChatConstant.PAGE_PUBLIC_ACCOUNT_JSAPI;
import static zx.soft.sns.parser.constant.WeChatConstant.PAGE_WEIXIN;
import static zx.soft.sns.parser.constant.WeChatConstant.QUERY;
import static zx.soft.sns.parser.constant.WeChatConstant.ROOT_URL;
import static zx.soft.sns.parser.constant.WeChatConstant.TYPE;
import static zx.soft.sns.parser.constant.WeChatConstant.TYPE_ARTICLE;
import static zx.soft.sns.parser.constant.WeChatConstant.TYPE_PUBLIC_ACCOUNT;
import zx.soft.utils.codec.URLCodecUtils;

/**
 * 微信可请求的URL类
 * 
 * @author wanggang
 *
 */
public class WeChatURL {

	/**
	 * 页面：微信公共号
	 */
	public static String getWeChatPAUrl(String query, int page, boolean otherParams) {
		String url = ROOT_URL + PAGE_WEIXIN + TYPE + "=" + TYPE_PUBLIC_ACCOUNT + "&" + QUERY + "="
				+ URLCodecUtils.encoder(query, "UTF-8") + "&" + PAGE + "=" + page;
		if (otherParams) {
			url += "&" + OTHER_PARAMS;
		}
		return url;
	}

	/**
	 * 页面：微信文章搜索
	 */
	public static String getWeChatAUrl(String query, int page, boolean otherParams) {
		String url = ROOT_URL + PAGE_WEIXIN + TYPE + "=" + TYPE_ARTICLE + "&" + QUERY + "="
				+ URLCodecUtils.encoder(query, "UTF-8") + "&" + PAGE + "=" + page;
		if (otherParams) {
			url += "&" + OTHER_PARAMS;
		}
		return url;
	}

	/**
	 * 页面：某个微信公共号及其文章，不支持翻页（需要研究”查看更多“功能）
	 */
	public static String getWeChatPADetailsUrl(String openid) {
		return ROOT_URL + PAGE_PUBLIC_ACCOUNT + OPEN_ID + "=" + openid;
	}

	/**
	 * JS接口：某个公共号的所有文章
	 */
	public static String getWeChatArticleApi(String openid, int page) {
		return ROOT_URL + PAGE_PUBLIC_ACCOUNT_JSAPI + OPEN_ID + "=" + openid + "&" + PAGE + "=" + page;
	}

}
