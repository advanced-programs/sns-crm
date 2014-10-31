package zx.soft.crm.controller;

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

import zx.soft.crm.model.MemberLevelInfo;
import zx.soft.crm.service.UserService;

@Controller
@RequestMapping("/users/{uid}/member_levels")
public class MemberLevelController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	MemberLevelInfo add(@PathVariable long uid, @RequestBody MemberLevelInfo memberLevelInfo) {
		memberLevelInfo.setUid(uid);
		return userService.add(memberLevelInfo);
	}

	@RequestMapping(value = "/{member_level_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long uid, @PathVariable int member_level_id) {
		userService.deleteMemberLevelInfo(uid, member_level_id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<MemberLevelInfo> list(@PathVariable long uid) {
		return userService.getMemberLevelInfos(uid);
	}

}
