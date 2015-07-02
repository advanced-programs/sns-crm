package zx.soft.sns.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.sns.api.dao.KeywordMapper;
import zx.soft.sns.api.dao.WeChatMapper;
import zx.soft.sns.api.domain.WeChatPublicAccount;
import zx.soft.sns.api.utils.AnalyzerTool;
import zx.soft.utils.chars.JavaPattern;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.log.LogbackUtil;

/**
 * OA全网任务缓存查询服务类
 *
 * @author wanggang
 *
 */
@Service
public class WeChatService {

	private static Logger logger = LoggerFactory.getLogger(WeChatService.class);

	@Inject
	private WeChatMapper weChatMapper;

	@Inject
	private KeywordMapper keywordMapper;

	@Inject
	private AnalyzerTool analyzerTool;

	/**
	 * 插入多个帐号
	 */
	public void insertWeChatPAs(List<WeChatPublicAccount> weChatPublicAccounts) {
		for (WeChatPublicAccount weChatPublicAccount : weChatPublicAccounts) {
			try {
				// 插入公共号信息
				insertWeChatPA(weChatPublicAccount);
				// 对公共号信息进行分词，并插入关键词组
				Set<String> keywords = analyzerTool.analyzerTextToList(weChatPublicAccount.getName() + ","
						+ weChatPublicAccount.getDescription() + "," + weChatPublicAccount.getVerify_info());
				insertKeywords(keywords);
			} catch (Exception e) {
				logger.error("Insert Exception:{},mid:{}", LogbackUtil.expection2Str(e), weChatPublicAccount.getWid());
			}
		}
	}

	private void insertWeChatPA(WeChatPublicAccount weChatPublicAccount) {
		weChatMapper.insertWeChatPA(weChatPublicAccount);
	}

	/**
	 * 查询多个帐号
	 */
	public List<WeChatPublicAccount> selectWeChatPAs(String wids) {
		List<WeChatPublicAccount> weChatPublicAccounts = new ArrayList<>();
		WeChatPublicAccount tmp = null;
		for (String wid : wids.split(",")) {
			tmp = selectWeChatPA(wid);
			if (tmp != null) {
				weChatPublicAccounts.add(tmp);
			}
		}
		return weChatPublicAccounts;
	}

	private WeChatPublicAccount selectWeChatPA(String wid) {
		return weChatMapper.selectWeChatPA(wid);
	}

	/**
	 * 更新多个帐号
	 */
	public void updateWeChatPAs(List<WeChatPublicAccount> weChatPublicAccounts) {
		for (WeChatPublicAccount weChatPublicAccount : weChatPublicAccounts) {
			try {
				updateWeChatPA(weChatPublicAccount);
			} catch (Exception e) {
				logger.error("Update Exception:{},mid:{}", LogbackUtil.expection2Str(e), weChatPublicAccount.getWid());
			}
		}
	}

	private void updateWeChatPA(WeChatPublicAccount weChatPAInsert) {
		weChatMapper.updateWeChatPA(weChatPAInsert);
	}

	/**
	 * 删除多个帐号
	 */
	public void deleteWeChatPAs(String wids) {
		for (String wid : wids.split(",")) {
			deleteWeChatPA(wid);
		}
	}

	/**
	 * 插入多个关键词
	 */
	public void insertKeywords(List<String> keywords) {
		for (String keyword : keywords) {
			insertKeyword(keyword);
		}
	}

	private void insertKeywords(Set<String> keywords) {
		for (String keyword : keywords) {
			insertKeyword(keyword);
		}
	}

	private void insertKeyword(String keyword) {
		try {
			// 排除含有数字字母以及一个字的关键词
			if (!JavaPattern.isAllNumAndLetter(keyword) && keyword.length() > 1) {
				keywordMapper.insertKeyword(CheckSumUtils.getCRC32(keyword), keyword);
			}
		} catch (Exception e) {
			logger.error("Insert Exception:{},md5:{}", LogbackUtil.expection2Str(e), CheckSumUtils.getCRC32(keyword));
		}
	}

	/**
	 * 读取多个关键词
	 */
	public List<String> selectKeywords(String num) {
		List<Map<String, Object>> keywords = keywordMapper.selectKeywords(num);
		List<String> words = new ArrayList<>();
		// 读取后即可删除
		for (Map<String, Object> keyword : keywords) {
			keywordMapper.deleteKeyword((int) keyword.get("id"));
			words.add((String) keyword.get("keyword"));
		}
		return words;
	}

	private void deleteWeChatPA(String wid) {
		weChatMapper.deleteWeChatPA(wid);
	}

}
