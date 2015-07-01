package zx.soft.sns.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zx.soft.sns.api.dao.WeChatMapper;
import zx.soft.sns.api.domain.WeChatPublicAccount;

/**
 * OA全网任务缓存查询服务类
 *
 * @author wanggang
 *
 */
@Service
public class WeChatService {

	@Inject
	private WeChatMapper weChatMapper;

	/**
	 * 插入多个帐号
	 */
	public void insertWeChatPAs(List<WeChatPublicAccount> weChatPublicAccounts) {
		for (WeChatPublicAccount weChatPublicAccount : weChatPublicAccounts) {
			insertWeChatPA(weChatPublicAccount);
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
			updateWeChatPA(weChatPublicAccount);
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
