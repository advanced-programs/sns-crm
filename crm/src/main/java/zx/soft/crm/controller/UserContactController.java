package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.model.UserContact;
import zx.soft.crm.service.UserService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM4:01
 * 用户最后交互信息表Controller
 */
@Controller
@RequestMapping("/users/{uid}/members/{mid}/userContact")
public class UserContactController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	UserContact add(@PathVariable long uid, @PathVariable long mid,
					@RequestBody UserContact userContact, @RequestParam(required = false) String isDebug) {
		userContact.setMid(mid);
		userContact.setUid(uid);
		return userService.addOrUpdate(userContact);
	}

}
