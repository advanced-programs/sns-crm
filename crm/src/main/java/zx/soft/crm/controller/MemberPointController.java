package zx.soft.crm.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.crm.model.MemberPointRecord;
import zx.soft.crm.model.Page;
import zx.soft.crm.model.RecordQueryCondition;
import zx.soft.crm.service.UserService;

@Controller
@RequestMapping("/users/{uid}/members/{mid}/points")
public class MemberPointController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addPointRecords(@PathVariable long uid, @PathVariable long mid, @RequestBody MemberPointRecord record) {
		record.setUid(uid).setMid(mid);
		userService.add(record);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Page getPointRecords(@PathVariable long uid, @PathVariable long mid, RecordQueryCondition condition) {
		condition.setUid(uid).setMid(mid);
		int total = userService.listPointRecordCount(condition);
		return new Page(condition.getPage(), condition.getPer_page(), total, userService.listPointRecord(condition));
	}

}
