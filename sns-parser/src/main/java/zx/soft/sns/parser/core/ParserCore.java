package zx.soft.sns.parser.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.parser.domain.Record;
import zx.soft.sns.parser.http.HttpUtils;

/**
 * 网页解析类
 * @author wanggang
 *
 */
public class ParserCore {

	private static Logger logger = LoggerFactory.getLogger(ParserCore.class);

	private static final String BASE_URL = "http://qun.594sgk.com/qq/";

	private static final String URL_SUFFIX = ".html";

	/**
	 *  测试函数
	 */
	public static void main(String[] args) throws IOException {

		String html = ParserCore.doGet(2632996);
		System.out.println(html);
		//		System.out.println(CharsetEncoding.changeCharset(html, CharsetEncoding.ISO_8859_1, CharsetEncoding.UTF_16LE));
	}

	public static String doGet(long id) throws HttpException, IOException {

		HttpClient client = new HttpClient();
		//		HttpMethod method = new GetMethod("http://qun.594sgk.com/s/?wd=" + id);
		HttpMethod method = new GetMethod("http://qun.594sgk.com/qq/" + id + ".html");
		method.setRequestHeader("Accept", "*/*");
		method.setRequestHeader("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36");
		method.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		method.setRequestHeader("Cookie",
				"PHPSESSID=fmf96l2jqlpckhse2fafl1l531; oauth2_key=a%3A1%3A%7Bs%3A10%3A%22slim.flash%22%3Ba%3A0%3A%7B%7D%7D");

		//		method.setRequestHeader("Host", "qun.594sgk.com");
		//		method.setRequestHeader("Connection", "keep-alive");
		//		method.setRequestHeader("Cache-Control", "max-age=0");
		//		method.setRequestHeader("Referer", "http://qun.594sgk.com/s/?wd=" + id);
		method.setRequestHeader("Content-Type", "text/html; charset=utf-8");

		int status = client.executeMethod(method);
		if (status == 200) {
			Header[] headers = method.getResponseHeaders(); //返回的HTTP头信息
			//			Header[] headers = method.getRequestHeaders(); //返回的HTTP头信息
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			return method.getResponseBodyAsString();
		} else {
			return "error";
		}

	}

	/**
	 * 根据qq号或者qq群号获取记录信息
	 * @param id
	 * @return
	 */
	public static List<Record> parserQQInfo(long id) {

		List<Record> result = new ArrayList<>();
		// 获取html页面
		System.out.println(BASE_URL + id + URL_SUFFIX);
		String html = HttpUtils.doGet(BASE_URL + id + URL_SUFFIX, "utf-8");
		System.out.println(html);

		return result;
	}

}
