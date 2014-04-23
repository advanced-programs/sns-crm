package zx.soft.sns.parser.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpUtils {

	private static Logger logger = Logger.getLogger(HttpUtils.class);

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 *
	 * @param url: 请求的URL地址
	 * @param queryString: 请求的查询参数,可以为null
	 * @param charset: 字符集
	 * @param pretty: 是否美化
	 * @return: 返回请求响应的HTML
	 */
	public static String doGet(String url, String queryString, String charset, boolean pretty) {

		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
			if (StringUtils.isNotBlank(queryString)) {
				method.setQueryString(URIUtil.encodeQuery(queryString));
			}
			client.executeMethod(method);
			System.out.println(method.getStatusCode());
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
						charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty) {
						response.append(line).append(System.getProperty("line.separator"));
					} else {
						response.append(line);
					}
				}
				reader.close();
			}
		} catch (URIException e) {
			logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			logger.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	/**
	 * 重载函数
	 */
	public static String doGet(String url, String charset) {
		return doGet(url, "", charset, Boolean.TRUE);
	}

	/**
	 * 测试函数
	 * @param args
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void main(String[] args) throws HttpException, IOException {

		//		System.out.println(HttpUtils.doGet("http://114.112.65.13:8111/sina/users/2161358634/basic", "utf-8"));
		//		System.out.println(HttpUtils.doGet(" http://60.169.74.26:8111/sina/users/2039654561/fromapi", "utf-8"));
		System.out.println(HttpUtils.doGet("http://qun.594sgk.com/s/?wd=1010437118", "gb2312"));

	}

}
