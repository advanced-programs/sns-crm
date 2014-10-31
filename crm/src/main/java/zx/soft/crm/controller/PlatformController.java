package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.model.Platform;
import zx.soft.crm.model.UserIncrement;
import zx.soft.crm.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM3:43
 * 平台信息表Controller
 */
@Controller
@RequestMapping("/users/{uid}")
public class PlatformController {

	public static DateFormat sinaDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	@Inject
	private UserService userService;

	@RequestMapping(value = "/members/{mid}/platform", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	Platform add(@PathVariable long uid, @PathVariable long mid,
				 @RequestBody Platform platform,
				 @RequestParam(required = false) String isDebug,
				 HttpServletRequest request) {
		platform.setMid(mid);
		platform.setUid(uid);
		return userService.addOrUpdate(platform);
	}

	@RequestMapping(value = "/platform", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	List<Map<String, Object>> queryByUid(@PathVariable long uid,@RequestParam int platform) {
		return userService.queryByUid(uid,platform);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	Object queryByUidAndDate(@PathVariable long uid, @RequestParam String startTime, @RequestParam String endTime) {
		try {
			Date start = sinaDateFormat.parse(startTime);
			Date end = sinaDateFormat.parse(endTime);
			List<UserIncrement> list = userService.queryByUidAndDate(uid, start, end);
			return new ResponseEntity<List<UserIncrement>>(list, HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/platform/{platform_user_id}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	List<Platform> queryByUidAndDate(@PathVariable long uid, @PathVariable String platform_user_id) {
		return userService.queryByUidAndUserId(uid,platform_user_id);
	}
}
