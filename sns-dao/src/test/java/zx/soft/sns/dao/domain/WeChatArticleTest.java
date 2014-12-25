package zx.soft.sns.dao.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import zx.soft.sns.dao.domain.wechat.WeChatArticle;

public class WeChatArticleTest {

	@Test
	public void testWeChatArticle() {
		WeChatArticle weChatArticle = new WeChatArticle.Builder("articleid", "测试标题", "http://www.baidu.com")
				.setContent("测试数据").setPicUrl("https://www.google.com").setTimestamp(new Date(1418365073L * 1000))
				.setWeChatName("test").setWeChatOpenId("abcdef").build();
		assertEquals("WeChatArticle:[aid=articleid,title=测试标题,url=http://www.baidu.com,picUrl=https://www.google.com,"
				+ "timestamp=Fri Dec 12 14:17:53 CST 2014,weChatName=test,weChatOpenId=abcdef,content=测试数据]",
				weChatArticle.toString());
	}

}
