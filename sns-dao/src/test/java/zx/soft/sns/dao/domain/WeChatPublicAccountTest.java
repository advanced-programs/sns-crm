package zx.soft.sns.dao.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeChatPublicAccountTest {

	@Test
	public void testWeChatPublicAccount() {
		WeChatPublicAccount weChatPublicAccount = new WeChatPublicAccount.Builder("abc", "test")
				.setDescription("test and test").setHeadUrl("http://www.baidu.com")
				.setLastArticleUrl("https://www.google.com").setOpenId("cba").setVerifyInfo("认证信息").build();
		assertEquals(
				"WeChatPublicAccount:[wid=abc,name=test,openId=cba,headUrl=http://www.baidu.com,description=test and test,"
						+ "verifyInfo=认证信息,lastArticleUrl=https://www.google.com]", weChatPublicAccount.toString());
	}

}
