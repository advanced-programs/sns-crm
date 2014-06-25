package zx.soft.sns.parser.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.parser.domain.WeixinRecord;
import zx.soft.sns.parser.utils.HttpUtils;
import zx.soft.sns.parser.utils.JsonUtils;

public class ParserWeixinCore {

	private static Logger logger = LoggerFactory.getLogger(ParserWeixinCore.class);

	private static final String BASE_URL = "http://weixin.sogou.com/weixin?";

	private static final String LEAF_URL = "&type=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1&";

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		ParserWeixinCore pwc = new ParserWeixinCore();
		List<WeixinRecord> records = pwc.parserWeixinInfo("冷笑话");
		System.out.println(JsonUtils.toJson(records));

	}

	public List<WeixinRecord> parserWeixinInfo(String keyword) {

		List<WeixinRecord> result = new ArrayList<>();
		// 获取html页面
		String html = getHtml(keyword, 1);
		// 解析
		Elements aLabels = Jsoup.parse(html).select("a");
		String openid;
		for (Element aLabel : aLabels) {
			Elements aTag = aLabel.select("div");
			if (aTag.toString().length() != 0) {
				openid = aLabel.attr("href");
				String verifyInfo = "";
				if (aLabel.select("p").size() > 1) {
					verifyInfo = aLabel.select("p").get(1).select("span").get(1).text();
				}
				String description = "";
				if (aLabel.select("p").size() > 0) {
					description = aLabel.select("p").get(0).select("span").get(1).text();
				}
				result.add(new WeixinRecord.Builder(aTag.get(1).select("h4").text().substring(4), aTag.get(1)
						.select("h3").text()).setOpenId(openid.substring(openid.indexOf("=") + 1))
						.setHeadUrl(aTag.get(0).select("img").attr("src")).setDescription(description)
						.setVerifyInfo(verifyInfo).build());
			}
		}

		return result;
	}

	private String getHtml(String keyword, int page) {
		String url = getUrl(keyword, page);
		logger.info("url=" + url);
		return HttpUtils.doGet(url, "UTF-8");
	}

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
