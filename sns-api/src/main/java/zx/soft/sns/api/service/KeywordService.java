package zx.soft.sns.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.sns.api.dao.KeywordMapper;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.log.LogbackUtil;

/**
 * OA全网任务缓存查询服务类
 *
 * @author wanggang
 *
 */
@Service
public class KeywordService {

	private static Logger logger = LoggerFactory.getLogger(KeywordService.class);

	@Inject
	private KeywordMapper keywordMapper;

	/**
	 * 插入多个关键词
	 */
	public void insertKeywords(List<String> keywords) {
		for (String keyword : keywords) {
			try {
				insertKeyword(keyword);
			} catch (Exception e) {
				logger.error("Insert Exception:{},md5:{}", LogbackUtil.expection2Str(e),
						CheckSumUtils.getCRC32(keyword));
			}
		}
	}

	private void insertKeyword(String keyword) {
		keywordMapper.insertKeyword(CheckSumUtils.getCRC32(keyword), keyword);
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

}
