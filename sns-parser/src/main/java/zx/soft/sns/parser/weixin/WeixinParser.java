package zx.soft.sns.parser.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.parser.domain.WeixinRecord;
import zx.soft.sns.parser.utils.JsoupUtils;
import zx.soft.utils.http.HttpUtils;
import zx.soft.utils.json.JsonUtils;

public class WeixinParser {

	private static Logger logger = LoggerFactory.getLogger(WeixinParser.class);

	private static final String BASE_URL = "http://weixin.sogou.com/weixin?";

	private static final String LEAF_URL = "&type=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1&";

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		WeixinParser pwc = new WeixinParser();
		List<WeixinRecord> records = pwc.parserWeixinInfo("中新软件");
		System.out.println(JsonUtils.toJson(records));

	}

	public List<WeixinRecord> parserWeixinInfo(String keyword) {

		// 获取html页面
		String html = getHtml(keyword, 1);

		List<WeixinRecord> result = JsoupUtils.parserWeixin(html);

		return result;
	}

	/**
	 * 获取页面文件
	 * @param keyword
	 * @param page
	 * @return
	 */
	private String getHtml(String keyword, int page) {
		String url = getUrl(keyword, page);
		logger.info("url=" + url);
		return HttpUtils.doGet(url, "UTF-8");
	}

	/**
	 * 获取GET请求URL
	 * @param keyword
	 * @param page
	 * @return
	 */
	private String getUrl(String keyword, int page) {
		String encodedURL = "";
		try {
			encodedURL = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return BASE_URL + "query=" + encodedURL + LEAF_URL + "page=" + page;
	}

}
