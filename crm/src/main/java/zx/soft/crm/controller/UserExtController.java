package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.model.UserExt;
import zx.soft.crm.service.UserService;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM4:12
 * 用户扩展表Controller
 */
@Controller
@RequestMapping("/users/{uid}")
public class UserExtController {

	@Inject
	private UserService userService;

	@RequestMapping(value = "/members/{mid}/userExt", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	UserExt add(@PathVariable long uid, @PathVariable long mid,
				@RequestBody UserExt userExt, @RequestParam(required = false) String isDebug) {
		userExt.setMid(mid);
		userExt.setUid(uid);
		return userService.addOrUpdate(userExt);
	}

	@RequestMapping(value = "/province", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	List<Map<String, Object>> queryProvinceByUid(@PathVariable long uid, @RequestParam int platform) {
		return userService.queryProvinceByUid(uid, platform);
	}

}
