package zx.soft.sns.parser.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.parser.domain.Record;
import zx.soft.sns.parser.utils.JsonUtils;

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

		List<Record> records = ParserCore.parserQQInfo(69591601L);
		System.out.println(JsonUtils.toJson(records));
	}


	/**
	 * 根据qq号或者qq群号获取记录信息
	 * @param id
	 * @return
	 */
	public static List<Record> parserQQInfo(long id) {

		List<Record> result = new ArrayList<>();
		// 获取html页面
		String html = null;
		try {
			html = doGet(id);
		} catch (IOException e) {
			logger.error("ParserCore.parserQQInfo IOException: " + e);
			throw new RuntimeException(e);
		}
		if (html == null) {
			logger.error("Id=" + id + " has no info.");
			return null;
		}
		Elements trs = Jsoup.parse(html).select("table").select("tbody").select("tr");
		System.out.println(trs.get(1).select("td").get(1));
		Elements tds = null;
		for (int i = 1; i < trs.size(); i++) {
			tds = trs.get(i).select("td");
			if (tds != null) {
				result.add(new Record.Builder(Long.parseLong(tds.get(0).text()), tds.get(1).text())
						.setSex("男".equals(tds.get(2).text()) ? Boolean.FALSE : Boolean.TRUE)
						.setAge(Integer.parseInt(tds.get(3).text())).setQqGroup(Long.parseLong(tds.get(4).text()))
						.build());
			} else {
				logger.info("Id="+id+" has no info.");
			}
			tds = null;
		}

		return result;
	}

	private static String doGet(long id) throws HttpException, IOException {

		HttpClient client = new HttpClient();
		//		HttpMethod method = new GetMethod("http://qun.594sgk.com/s/?wd=" + id);
		HttpMethod method = new GetMethod(BASE_URL + id + URL_SUFFIX);
		method.setRequestHeader("Accept", "*/*");
		method.setRequestHeader("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36");
		method.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		method.setRequestHeader("Cookie",
				"PHPSESSID=fmf96l2jqlpckhse2fafl1l531; oauth2_key=a%3A1%3A%7Bs%3A10%3A%22slim.flash%22%3Ba%3A0%3A%7B%7D%7D");

		int status = client.executeMethod(method);
		if (status == 200) {
			return unCompressToStr(method.getResponseBodyAsStream(), "gb2312");
		} else {
			throw new RuntimeException("statusCode=" + status);
		}

	}

	/**
	 * 网页Gzip数据解压
	 */
	private static String unCompressToStr(InputStream in, String charset) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString(charset);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
