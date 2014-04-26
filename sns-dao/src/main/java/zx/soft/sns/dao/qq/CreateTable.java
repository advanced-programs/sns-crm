package zx.soft.sns.dao.qq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建初始数据表
 * @author wanggang
 *
 */
public class CreateTable {

	private static Logger logger = LoggerFactory.getLogger(CreateTable.class);

	private static final String QQ_INFO_TABLE = "qq_info_";

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		SnsJDBC snsJDBC = new SnsJDBC();
		for (int i = 0; i < 32; i++) {
			logger.info("Creating at: " + i);
			snsJDBC.createQQTable(QQ_INFO_TABLE + i);
		}
		snsJDBC.close();

	}

}
