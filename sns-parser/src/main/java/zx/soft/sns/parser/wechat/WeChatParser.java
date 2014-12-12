package zx.soft.sns.parser.wechat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.domain.WeChatPublicAccount;
import zx.soft.utils.http.HttpUtils;

public class WeChatParser {

	private static Logger logger = LoggerFactory.getLogger(WeChatParser.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		WeChatParser pwc = new WeChatParser();
		List<WeChatPublicAccount> records = pwc.parserWeChatPublicAccount("娱乐");
		//		System.out.println(JsonUtils.toJson(records));
	}

	/**
	 * 页面：微信公共号解析
	 */
	public List<WeChatPublicAccount> parserWeChatPublicAccount(String query) {
		logger.info("query={}", query);
		// 获取URL
		String url = WeChatURL.getWeChatPAUrl(query, 1, Boolean.FALSE);
		logger.info("Request url={}", url);
		// 获取html页面
		String html = HttpUtils.doGet(url, "UTF-8");
		System.out.println(html);
		//		String html = getHtml(keyword, 1);
		//		List<WeChatPublicAccount> result = JsoupUtils.parserWeixin(html);
		return null;
	}

}
