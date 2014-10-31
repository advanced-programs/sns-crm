package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.model.UserLastActive;
import zx.soft.crm.service.UserService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM4:17
 * 用户最后交互信息表Controller
 */
@Controller
@RequestMapping("/users/{uid}/members/{mid}/userLastActive")
public class UserLastActiveController {
	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	UserLastActive add(@PathVariable long uid, @PathVariable long mid,
					   @RequestBody UserLastActive userLastActive, @RequestParam(required = false) String isDebug) {
		userLastActive.setMid(mid);
		userLastActive.setUid(uid);
		return userService.addOrUpdate(userLastActive);
	}

}
