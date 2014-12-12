package zx.soft.sns.parser.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zx.soft.sns.dao.domain.WeChatPublicAccount;

/**
 * 解析页面工具
 * 
 * @author wanggang
 *
 */
public class JsoupUtils {

	/**
	 * 解析微信页面信息
	 * 
	 * @param html
	 * @return
	 */
	public static List<WeChatPublicAccount> parserWeixin(String html) {

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
