package zx.soft.sns.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.sns.api.domain.WeChatPublicAccount;
import zx.soft.sns.api.service.WeChatService;

/**
 *  微信公共号CURD接口
 *
 * 接口说明：
 *       1、POST:   http://localhost:8888/wechat/accounts
 *                     list对象
 *       2、DELETE: http://localhost:8888/wechat/accounts/{wids}
 *            renrenjiaoyou,ios3721,xiaoqingxin222
 *       3、GET：   http://localhost:8888/wechat/accounts/{wids}
 *            renrenjiaoyou,ios3721,xiaoqingxin222
 *       4、PUT：   http://localhost:8888/wechat/accounts
 *                     list对象
 *
 * @author wanggang
 *
 */
@Controller
@RequestMapping("/wechat/accounts")
public class WeChatController {

	@Inject
	private WeChatService weChatService;

	/**
	 * 插入多个帐号
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addWeChatPAs(@RequestBody List<WeChatPublicAccount> weChatPublicAccounts) {
		weChatService.insertWeChatPAs(weChatPublicAccounts);
	}

	/**
	 * 查询多个帐号
	 */
	@RequestMapping(value = "/{wids}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<WeChatPublicAccount> retriveWeChatPAs(@PathVariable String wids) {
		return weChatService.selectWeChatPAs(wids);
	}

	/**
	 * 更新多个帐号
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateWeChatPAs(@RequestBody List<WeChatPublicAccount> weChatPublicAccounts) {
		weChatService.updateWeChatPAs(weChatPublicAccounts);
	}

	/**
	 * 删除多个帐号
	 */
	@RequestMapping(value = "/{wids}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteWeChatPAs(@PathVariable String wids) {
		weChatService.deleteWeChatPAs(wids);
	}

}
