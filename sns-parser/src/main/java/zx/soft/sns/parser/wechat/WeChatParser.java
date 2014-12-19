package zx.soft.sns.parser.wechat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.domain.WeChatArticle;
import zx.soft.sns.dao.domain.WeChatPublicAccount;
import zx.soft.sns.parser.utils.JsoupUtils;
import zx.soft.utils.http.HttpUtils;
import zx.soft.utils.json.JsonUtils;

public class WeChatParser {

	private static Logger logger = LoggerFactory.getLogger(WeChatParser.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		WeChatParser pwc = new WeChatParser();
		List<WeChatArticle> records = pwc.parserWeChatArticles("oIWsFt8qnA4wjDBfjG9L1C8S2hG8", 2);
		System.out.println(JsonUtils.toJson(records));
	}

	/**
	 * JS接口：某个公共号的所有文章解析
	 * 测试：oIWsFt8qnA4wjDBfjG9L1C8S2hG8
	 */
	public List<WeChatArticle> parserWeChatArticles(String openid, int page) {
		logger.info("WeChatArticles openid={}, page={}", openid, page);
		// 获取URL
		String url = WeChatURL.getWeChatArticleApi(openid, page);
		logger.info("WeChatArticles Request url={}", url);
		// 获取html页面
		String xml = HttpUtils.doGet(url, "UTF-8");
		List<WeChatArticle> result = JsoupUtils.parserWeChatArticles(xml);
		return result;
	}

	/**
	 * 页面：微信文章搜索解析
	 */
	public List<WeChatArticle> parserWeChatArticle(String keyword, int page) {
		logger.info("WeChatArticle query={}", keyword);
		// 获取URL
		String url = WeChatURL.getWeChatAUrl(keyword, page, Boolean.FALSE);
		logger.info("WeChatArticle Request url={}", url);
		// 获取html页面
		String html = HttpUtils.doGet(url, "UTF-8");
		List<WeChatArticle> result = JsoupUtils.parserWeChatArticle(html);
		return result;
	}

	/**
	 * 页面：微信公共号解析
	 */
	public List<WeChatPublicAccount> parserWeChatPublicAccount(String keyword, int page) {
		logger.info("WeChatPublicAccount query={}", keyword);
		// 获取URL
		String url = WeChatURL.getWeChatPAUrl(keyword, page, Boolean.FALSE);
		logger.info("WeChatPublicAccount Request url={}", url);
		// 获取html页面
		String html = HttpUtils.doGet(url, "UTF-8");
		List<WeChatPublicAccount> result = JsoupUtils.parserWeChatPublicAccount(html);
		return result;
	}

}
