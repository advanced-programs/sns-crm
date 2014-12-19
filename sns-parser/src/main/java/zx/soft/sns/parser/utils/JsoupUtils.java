package zx.soft.sns.parser.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.domain.WeChatArticle;
import zx.soft.sns.dao.domain.WeChatPublicAccount;
import zx.soft.sns.parser.domain.ArticlesResponse;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.json.JsonUtils;

/**
 * 解析页面工具
 * 
 * @author wanggang
 *
 */
public class JsoupUtils {

	private static Logger logger = LoggerFactory.getLogger(JsoupUtils.class);

	/**
	 * JS接口：某个公共号的所有文章解析，有待完善。。。
	 */
	public static List<WeChatArticle> parserWeChatArticles(String xml) {

		List<WeChatArticle> result = new ArrayList<>();
		// 解析
		int index1 = xml.indexOf("(");
		int index2 = xml.lastIndexOf(")");
		String jsonStr = xml.substring(index1 + 1, index2);
		ObjectMapper objectMapper = JsonUtils.getObjectMapper();
		ArticlesResponse articlesResponse = null;
		try {
			articlesResponse = objectMapper.readValue(jsonStr, ArticlesResponse.class);
		} catch (IOException e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		}
		System.out.println(JsonUtils.toJson(articlesResponse));
		/**
		 * 需要增加XML文件解析代码。。。
		 */

		return result;
	}

	/**
	 * 解析微信文章搜索信息
	 */
	public static List<WeChatArticle> parserWeChatArticle(String html) {

		List<WeChatArticle> result = new ArrayList<>();
		// 解析
		Elements divs = Jsoup.parse(html).select("div.results").select("div.wx-rb");
		if (divs.size() == 0) {
			return result;
		}
		String url = "";
		for (Element div : divs) {
			url = div.select("div.img_box2").select("a").attr("href");
			result.add(new WeChatArticle.Builder(CheckSumUtils.getMD5(url), div.select("h4").text(), url)
					.setContent(div.select("p").text())
					.setPicUrl(div.select("div.img_box2").select("a").select("img").attr("src"))
					.setTimestamp(new Date(Long.parseLong(div.select("div.s-p").attr("t")) * 1000))
					.setWeChatName(div.select("div.s-p").select("a").attr("title"))
					.setWeChatOpenId(div.select("div.s-p").select("a").attr("href").substring(12)).build());
		}

		return result;
	}

	/**
	 * 解析微信公共号信息
	 */
	public static List<WeChatPublicAccount> parserWeChatPublicAccount(String html) {

		List<WeChatPublicAccount> result = new ArrayList<>();
		// 解析
		Elements divs = Jsoup.parse(html).select("div[href]");
		if (divs.size() == 0) {
			return result;
		}
		String verifyInfo = "", lastArticleUrl = "";
		for (Element div : divs) {
			if (div.select("p").size() > 2) {
				verifyInfo = div.select("p").get(1).select("span").get(1).text();
				lastArticleUrl = div.select("p").get(2).select("a").attr("href");
			} else if (div.select("p").size() > 1) {
				lastArticleUrl = div.select("p").get(1).select("a").attr("href");
			}
			result.add(new WeChatPublicAccount.Builder(div.select("h4").text().substring(4), div.select("h3").text())
					.setOpenId(div.attr("href").substring(12)).setHeadUrl(div.select("img").get(0).attr("src"))
					.setDescription(div.select("p").get(0).select("span").get(1).text()).setVerifyInfo(verifyInfo)
					.setLastArticleUrl(lastArticleUrl).build());
			verifyInfo = "";
			lastArticleUrl = "";
		}

		return result;
	}

}
