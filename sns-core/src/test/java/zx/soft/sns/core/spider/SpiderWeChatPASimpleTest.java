package zx.soft.sns.core.spider;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpiderWeChatPASimpleTest {

	@Test
	public void testCache() {
		SpiderWeChatPASimple sws = new SpiderWeChatPASimple();
		sws.addKeyword("关键词1");
		sws.addKeyword("关键词2");
		sws.addKeyword("关键词3");
		assertEquals(3, sws.getKeywordscache().size());
		sws.popKeyword();
		assertEquals(2, sws.getKeywordscache().size());
		sws.popKeyword();
		assertEquals(1, sws.getKeywordscache().size());
		sws.popKeyword();
		assertEquals(0, sws.getKeywordscache().size());
	}

}
