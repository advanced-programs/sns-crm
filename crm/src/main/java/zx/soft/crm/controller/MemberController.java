package zx.soft.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zx.soft.crm.model.*;
import zx.soft.crm.service.UserService;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users/{uid}/members")
public class MemberController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	User add(@PathVariable long uid,@RequestBody User user) {
		user.setUid(uid);
		return userService.add(user);
	}

	@RequestMapping(value = "/{mid}/member_info", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public
	@ResponseBody
	Member addMember(@PathVariable long uid, @PathVariable long mid, @RequestBody Member member) {
		member.setUid(uid).setMid(mid);
		return userService.addOrUpdate(member);
	}

	@RequestMapping(value = "/{mid}/member_all", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllByUidAndMid(@PathVariable long uid, @PathVariable long mid) {
		userService.deleteAllByUidAndMid(uid, mid);
	}

	@RequestMapping(value = "/{mid}/member_info", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable long uid, @PathVariable long mid) {
		userService.deleteMember(uid, mid);
	}

	@RequestMapping(value = "/{mid}", method = RequestMethod.GET)
	public
	@ResponseBody
	User get(@PathVariable long uid, @PathVariable long mid) {
		return userService.getUser(uid, mid);
	}

	@RequestMapping(value = "/{mid}/member_info", method = RequestMethod.GET)
	public
	@ResponseBody
	Member getMember(@PathVariable long uid, @PathVariable long mid) {
		return userService.getMember(uid, mid);
	}

	@RequestMapping(method = RequestMethod.GET)
	public
	@ResponseBody
	Page list(@PathVariable long uid, UserQueryCondition condition) {
		condition.setUid(uid);
		condition.setDefualt();
		int total = userService.countList(condition);
		return new Page(condition.getPage(), condition.getPer_page(), total, userService.list(condition));
	}

	@RequestMapping(value = "/{mid}/exps_and_points", method = RequestMethod.GET)
	public
	@ResponseBody
	Page listPointAndExp(@PathVariable long uid, @PathVariable long mid, RecordQueryCondition condition) {
		condition.setUid(uid).setMid(mid);
		int total = userService.listExpAndPointRecordCount(condition);
		return new Page(condition.getPage(), condition.getPer_page(), total,
				userService.listExpAndPointRecord(condition));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public
	@ResponseBody
	Map<String, Integer> queryCountByUid(@PathVariable long uid) {
		Map<String, Integer> map = new HashMap<>();
		map.put("userCount", userService.queryUserCountByUid(uid));
		map.put("memberCount", userService.queryMemberCountByUid(uid));
		return map;
	}

	@RequestMapping(value = "/{mid}/tags", method = RequestMethod.GET)
	public
	@ResponseBody
	List<TagInfo> queryTagInfo(@PathVariable long uid, @PathVariable long mid) {
		return userService.queryTagInfo(uid, mid);
	}

	@RequestMapping(value = "/{mid}", method = RequestMethod.POST)
	public
	@ResponseBody
	User update(@PathVariable long uid, @PathVariable long mid, @RequestBody User user) {
		user.setUid(uid).setMid(mid);
		return userService.update(user);
	}

}