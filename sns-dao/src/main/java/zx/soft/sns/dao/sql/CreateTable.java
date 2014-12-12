package zx.soft.sns.dao.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建初始数据表
 * 
 * @author wanggang
 *
 */
public class CreateTable {

	private static Logger logger = LoggerFactory.getLogger(CreateTable.class);

	public static final String WECHAT_PUBLIC_ACCOUNTS = "wechat_public_accounts";

	public static final String WECHAT_ARTICLES = "wechat_articles";

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		SnsDbcp snsDbcp = new SnsDbcp();
		logger.info("Creating WeChatPublicAccountTable ...");
		snsDbcp.createWeChatPublicAccountTable(WECHAT_PUBLIC_ACCOUNTS);
		logger.info("Creating WeChatArticleTable ...");
		snsDbcp.createWeChatArticleTable(WECHAT_ARTICLES);
		snsDbcp.close();
	}

}
