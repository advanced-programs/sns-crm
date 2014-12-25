import java.util.List;

import zx.soft.sns.dao.domain.qq.QQAccount;
import zx.soft.sns.parser.qq.QQParser;

public class Demo {

	public static void main(String[] args) {

		QQParser qqParser = new QQParser();
		List<QQAccount> result = qqParser.parserQQAccount(10001L);
	}

}
