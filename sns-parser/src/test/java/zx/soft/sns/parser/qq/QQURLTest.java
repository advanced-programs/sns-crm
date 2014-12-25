package zx.soft.sns.parser.qq;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QQURLTest {

	@Test
	public void testGetQQUrl() {
		String url = QQURL.getQQUrl(10001L);
		assertEquals("http://qun.594sgk.com/s/?wd=10001", url);
	}

}
