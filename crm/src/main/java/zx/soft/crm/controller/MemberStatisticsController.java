package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.service.UserService;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-24
 * Time: PM3:34
 * 用户统计数据
 */
@Controller
@RequestMapping("/users/{uid}")
public class MemberStatisticsController {

	@Inject
	private UserService userService;

	@RequestMapping(value = "/gender", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	List<Map<String, Object>> queryGenderByUid(@PathVariable long uid, @RequestParam int platform) {
		return userService.queryGenderByUid(uid, platform);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	List<Map<String, Object>> queryStatusByUid(@PathVariable long uid,@RequestParam int platform) {
		return userService.queryStatusByUid(uid, platform);
	}
}
