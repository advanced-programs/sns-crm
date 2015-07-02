package zx.soft.sns.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.sns.api.dao.WeChatMapper;
import zx.soft.sns.api.domain.WeChatPublicAccount;
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

	/**
	 * 插入多个帐号
	 */
	public void insertWeChatPAs(List<WeChatPublicAccount> weChatPublicAccounts) {
		for (WeChatPublicAccount weChatPublicAccount : weChatPublicAccounts) {
			try {
				insertWeChatPA(weChatPublicAccount);
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

	private void deleteWeChatPA(String wid) {
		weChatMapper.deleteWeChatPA(wid);
	}

}
