package zx.soft.sns.core.spider;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.core.utils.AnalyzerTool;
import zx.soft.sns.core.wechat.WeChatCore;
import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.wechat.WeChatDaoImpl;
import zx.soft.sns.parser.wechat.WeChatParser;
import zx.soft.utils.chars.JavaPattern;

/**
 * 微信公共号爬取简单实现：单线程+无Redis缓存
 * 
 * @author wanggang
 *
 */
public class SpiderWeChatPASimple {

	private static Logger logger = LoggerFactory.getLogger(SpiderWeChatPASimple.class);

	// 缓存表
	private final Set<String> keywordsCache = new TreeSet<String>();

	// 分词器
	private static final AnalyzerTool ANALYZER_TOOL = new AnalyzerTool();
	// 微信数据采集并持久化到数据库核心工具类
	private final WeChatCore weChatCore;

	public SpiderWeChatPASimple() {
		this.weChatCore = new WeChatCore(new WeChatParser(), new WeChatDaoImpl(MybatisConfig.ServerEnum.sns));
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		SpiderWeChatPASimple sws = new SpiderWeChatPASimple();
		sws.pullWeChatPublicAccounts("娱乐");
	}

	/**
	 * 采集微信公共帐号数据
	 * 
	 * @param seed：种子关键词
	 * @param limit：限制采集的关键词个数
	 */
	public void pullWeChatPublicAccounts(String seed) {
		// 爬取微信公共帐号，并存储到数据库，返回所有帐号名称组合字符串
		String names = weChatCore.retriveWeChatPA2Db(seed);
		// 将新的关键词列表添加到缓存中
		addKeywords(ANALYZER_TOOL.analyzerTextToArr(names));
		int count = 1;
		while (true) {
			logger.info("Pulling WeChatPublicAccount at {}.", count++);
			// 爬取并下载
			names = weChatCore.retriveWeChatPA2Db(popKeyword());
			// 增加列表
			addKeywords(ANALYZER_TOOL.analyzerTextToArr(names));
		}
	}

	/**
	 * 添加关键词
	 */
	public void addKeyword(String keyword) {
		if (!JavaPattern.isAllNumAndLetter(keyword)) {
			keywordsCache.add(keyword);
		}
	}

	private void addKeywords(String... keywords) {
		for (String keyword : keywords) {
			addKeyword(keyword);
		}
	}

	/**
	 * 随机获取一个关键词，并从列表中删除
	 */
	public String popKeyword() {
		String result = "";
		Iterator<String> iterator = keywordsCache.iterator();
		if (iterator.hasNext()) {
			result = iterator.next();
		} else {
			throw new RuntimeException("Cache's size is zero.");
		}
		keywordsCache.remove(result);
		return result;
	}

	public Set<String> getKeywordscache() {
		return keywordsCache;
	}

}
