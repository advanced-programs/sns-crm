package zx.soft.sns.core.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.core.spider.SpiderWeChatPAMain;
import zx.soft.sns.core.spider.SpiderWeChatPASimple;

/**
 * 驱动类
 *
 */
public class SNSCoreDriver {

	private static Logger logger = LoggerFactory.getLogger(SNSCoreDriver.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("Usage: Driver <class-name>");
			System.exit(-1);
		}
		String[] leftArgs = new String[args.length - 1];
		System.arraycopy(args, 1, leftArgs, 0, leftArgs.length);

		switch (args[0]) {
		case "spiderWeixinMain":
			logger.info("爬取QQ数据： ");
			SpiderWeChatPAMain.main(leftArgs);
			break;
		case "spiderWeChatPASimple":
			logger.info("爬取微信公共帐号数据： ");
			SpiderWeChatPASimple.main(leftArgs);
			break;
		default:
			return;
		}

	}

}
