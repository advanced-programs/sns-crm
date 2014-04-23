package zx.soft.sns.parser.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 驱动类
 *
 */
public class SNSParserDriver {

	private static Logger logger = LoggerFactory.getLogger(SNSParserDriver.class);

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
		case "importSentimentData":
			logger.info("索引舆情数据： ");
			break;
		default:
			return;
		}

	}

}
