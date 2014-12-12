package zx.soft.sns.parser.wechat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeChatURLTest {

	@Test
	public void testGetWeChatPAUrl() {
		String url1 = "http://weixin.sogou.com/weixin?type=1&query=%E5%A8%B1%E4%B9%90&page=2";
		String url2 = "http://weixin.sogou.com/weixin?type=1&query=%E5%A8%B1%E4%B9%90&page=2&ie=utf8&_ast=1418312960&_asf=null&w=01029901&p=40040100&dp=1&cid=null";
		assertEquals(url1, WeChatURL.getWeChatPAUrl("娱乐", 2, Boolean.FALSE));
		assertEquals(url2, WeChatURL.getWeChatPAUrl("娱乐", 2, Boolean.TRUE));
	}

	@Test
	public void testGetWeChatAUrl() {
		String url1 = "http://weixin.sogou.com/weixin?type=2&query=%E5%A8%B1%E4%B9%90&page=2";
		String url2 = "http://weixin.sogou.com/weixin?type=2&query=%E5%A8%B1%E4%B9%90&page=2&ie=utf8&_ast=1418312960&_asf=null&w=01029901&p=40040100&dp=1&cid=null";
		assertEquals(url1, WeChatURL.getWeChatAUrl("娱乐", 2, Boolean.FALSE));
		assertEquals(url2, WeChatURL.getWeChatAUrl("娱乐", 2, Boolean.TRUE));
	}

	@Test
	public void testGetWeChatPADetailsUrl() {
		assertEquals("http://weixin.sogou.com/gzh?openid=oIWsFt8yedsB-ETGKrWnqij7QjcE",
				WeChatURL.getWeChatPADetailsUrl("oIWsFt8yedsB-ETGKrWnqij7QjcE"));
	}

	@Test
	public void testGetWeChatArticleApi() {
		assertEquals("http://weixin.sogou.com/gzhjs?openid=oIWsFt8yedsB-ETGKrWnqij7QjcE&page=2",
				WeChatURL.getWeChatArticleApi("oIWsFt8yedsB-ETGKrWnqij7QjcE", 2));
	}

}
