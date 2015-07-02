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

import zx.soft.sns.api.service.KeywordService;

/**
 *  微信公共号CURD接口
 *
 * 接口说明：
 *       1、POST:   http://localhost:8888/wechat/keywords
 *                            list对象
 *       2、GET：   http://localhost:8888/wechat/keywords/{num}
 *
 * @author wanggang
 *
 */
@Controller
@RequestMapping("/wechat/keywords")
public class KeywordController {

	@Inject
	private KeywordService keywordService;

	/**
	 * 插入多个关键词
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addKeywords(@RequestBody List<String> keywords) {
		keywordService.insertKeywords(keywords);
	}

	/**
	 * 查询多个关键词
	 */
	@RequestMapping(value = "/{num}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<String> retriveKeywords(@PathVariable String num) {
		return keywordService.selectKeywords(num);
	}

}
