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

import zx.soft.crm.model.TagInfo;
import zx.soft.crm.service.UserService;

@Controller
@RequestMapping("/users/{uid}/tags")
public class TagController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	TagInfo add(@PathVariable long uid, @RequestBody TagInfo tagInfo) {
		tagInfo.setUid(uid);
		return userService.add(tagInfo);
	}

	@RequestMapping(value = "/{tag_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long uid, @PathVariable int tag_id) {
		userService.deleteTagInfo(uid, tag_id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<TagInfo> getList(@PathVariable long uid) {
		return userService.getTagInfos(uid);
	}

}
