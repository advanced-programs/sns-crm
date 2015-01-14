package zx.soft.sns.core.wechat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.domain.wechat.WeChatPAInsert;
import zx.soft.sns.dao.domain.wechat.WeChatPublicAccount;
import zx.soft.sns.dao.wechat.WeChatDaoImpl;
import zx.soft.sns.parser.wechat.WeChatParser;

/**
 * 微信公共帐号/文章信息爬取核心类
 * 
 * @author wanggang
 *
 */
public class WeChatCore {

	private static Logger logger = LoggerFactory.getLogger(WeChatCore.class);

	// 解析工具
	private final WeChatParser weChatParser;
	// 持久化工具
	private final WeChatDaoImpl weChatDaoImpl;
	// 公共帐号页数限制
	private final int PA_LIMIT_PAGE = 20;
	// 微信公共帐号数据表
	private static final String WECHAT_PUBLIC_ACCOUNTS = "wechat_public_accounts";

	public WeChatCore(WeChatParser weChatParser, WeChatDaoImpl weChatDaoImpl) {
		this.weChatParser = weChatParser;
		this.weChatDaoImpl = weChatDaoImpl;
	}

	/**
	 * 测试代码
	 */
	public static void main(String[] args) {
		WeChatCore weChatCore = new WeChatCore(new WeChatParser(), new WeChatDaoImpl(MybatisConfig.ServerEnum.sns));
		weChatCore.retriveWeChatPA2Db("娱乐");
	}

	/**
	 * 微信公共帐号翻页爬取，并存储
	 * 最多爬取20页
	 */
	public String retriveWeChatPA2Db(String keyword) {
		String result = "";
		List<WeChatPublicAccount> weChats = null;
		int count;
		for (int i = 1; i <= PA_LIMIT_PAGE; i++) {
			weChats = weChatParser.parserWeChatPublicAccount(keyword, i);
			count = 0;
			if (weChats != null) {
				for (WeChatPublicAccount weChat : weChats) {
					if (!weChatDaoImpl.isWeChatPAExisted(WECHAT_PUBLIC_ACCOUNTS, weChat.getWid())) {
						weChatDaoImpl.insertWeChatPA(new WeChatPAInsert.Builder(WECHAT_PUBLIC_ACCOUNTS,
								weChat.getWid(), weChat.getName()).setDescription(weChat.getDescription())
								.setHeadUrl(weChat.getHeadUrl()).setLastArticleUrl(weChat.getLastArticleUrl())
								.setOpenId(weChat.getOpenId()).setVerifyInfo(weChat.getVerifyInfo()).build());
						result += weChat.getName();
					} else {
						count++;
					}
				}
				if (count == weChats.size()) {
					logger.info("Query={} related WeChatPublicAccount num is {} approximately.", keyword, (i - 1) * 10);
					break;
				}
			} else {
				logger.info("Query={} related WeChatPublicAccount num is {} approximately.", keyword, (i - 1) * 10);
				try {
					Thread.sleep(30 * 60 * 1000);
				} catch (InterruptedException e) {
					//
				}
			}
		}
		return result;
	}

	/**
	 * 微信文章翻页爬取，并存储
	 * 页数好像无限制
	 */
	// 待完善。。。

}
