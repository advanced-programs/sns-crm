package zx.soft.crm.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.crm.model.UserTag;
import zx.soft.crm.service.UserService;

@Controller
@RequestMapping("/users/{uid}/members/{mid}/tags")
public class MemberTagController {

	@Inject
	private UserService userService;

	@RequestMapping(value = "/{tag_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	UserTag add(@PathVariable long uid, @PathVariable long mid, @PathVariable int tag_id) {
		return userService.add(new UserTag(uid, mid, tag_id));
	}

	@RequestMapping(value = "/{tag_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long uid, @PathVariable long mid, @PathVariable int tag_id) {
		userService.delete(new UserTag(uid, mid, tag_id));
	}

}
