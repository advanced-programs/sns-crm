package zx.soft.sns.parser.qq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.domain.qq.QQAccount;
import zx.soft.sns.parser.utils.JsoupUtils;
import zx.soft.utils.json.JsonUtils;

public class QQParser {

	private static Logger logger = LoggerFactory.getLogger(QQParser.class);

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		QQParser qqParser = new QQParser();
		List<QQAccount> result = qqParser.parserQQAccount(10001L);
		System.out.println(JsonUtils.toJson(result));
	}

	/**
	 * QQ号或QQ群信息抓取并解析
	 * 测试：10001
	 */
	public List<QQAccount> parserQQAccount(long qq) {
		logger.info("QQAccount qq={}", qq);
		// 获取URL
		String url = QQURL.getQQUrl(qq);
		// 获取html页面
		String xml = doGet(url);
		// 解析并返回结果
		List<QQAccount> result = JsoupUtils.parserQQAccounts(xml);
		return result;
	}

	/**
	 * GET请求
	 */
	private String doGet(String url) {
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		method.setRequestHeader("Host", "qun.594sgk.com");
		method.setRequestHeader("Connection", "keep-alive");
		method.setRequestHeader("Cache-Control", "max-age=0");
		method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
		method.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		method.setRequestHeader(
				"Cookie",
				"__cfduid=d71eb363e1c1a82c794081389d111711f1419490727; ASPSESSIONIDAQRACQAS=HMEMKMIBLINFJKCHMIEBMGGI; CNZZDATA1000125360=1982804276-1419487810-%7C1419487810");
		try {
			int status = client.executeMethod(method);
			if (status == 200) {
				return unCompressToStr(method.getResponseBodyAsStream(), "gb2312");
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		} finally {
			method.abort();
			//			client.getHttpConnectionManager().closeIdleConnections(60 * 1000);
		}
	}

	/**
	 * 网页Gzip数据解压
	 */
	private static String unCompressToStr(InputStream in, String charset) {
		// stream需要close，否则出现java.net.SocketException: Too many open files异常。
		try (ByteArrayOutputStream out = new ByteArrayOutputStream(); //
				GZIPInputStream gunzip = new GZIPInputStream(in);) {
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
