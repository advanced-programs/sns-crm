package zx.soft.sns.parser.wechat;

import java.util.List;

import zx.soft.sns.dao.domain.wechat.WeChatArticle;
import zx.soft.sns.dao.domain.wechat.WeChatPublicAccount;
import zx.soft.sns.parser.utils.JsoupUtils;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;

public class WeChatParser {

	private HttpClientDaoImpl httpClient;

	public WeChatParser() {
		httpClient = new HttpClientDaoImpl();
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		WeChatParser pwc = new WeChatParser();
		List<WeChatPublicAccount> records = pwc.parserWeChatPublicAccount("娱乐", 2);
		System.out.println(JsonUtils.toJson(records));
	}

	/**
	 * JS接口：某个公共号的所有文章解析
	 * 测试：oIWsFt8qnA4wjDBfjG9L1C8S2hG8
	 */
	public List<WeChatArticle> parserWeChatArticles(String openid, int page) {
		// 获取URL
		String url = WeChatURL.getWeChatArticleApi(openid, page);
		// 获取html页面
		String xml = httpClient.doGet(url, "UTF-8");
		List<WeChatArticle> result = JsoupUtils.parserWeChatArticles(xml);
		return result;
	}

	/**
	 * 页面：微信文章搜索解析
	 */
	public List<WeChatArticle> parserWeChatArticle(String keyword, int page) {
		// 获取URL
		String url = WeChatURL.getWeChatAUrl(keyword, page, Boolean.FALSE);
		// 获取html页面
		String html = httpClient.doGet(url, "UTF-8");
		List<WeChatArticle> result = JsoupUtils.parserWeChatArticle(html);
		return result;
	}

	/**
	 * 页面：微信公共号解析
	 */
	public List<WeChatPublicAccount> parserWeChatPublicAccount(String keyword, int page) {
		// 获取URL
		String url = WeChatURL.getWeChatPAUrl(keyword, page, Boolean.FALSE);
		// 获取html页面
		String html = httpClient.doGet(url, "UTF-8");
		List<WeChatPublicAccount> result = JsoupUtils.parserWeChatPublicAccount(html);
		return result;
	}

}
