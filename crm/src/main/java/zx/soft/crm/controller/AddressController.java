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

import zx.soft.crm.model.Address;
import zx.soft.crm.service.UserService;

@Controller
@RequestMapping("/users/{uid}/members/{mid}/addresses")
public class AddressController {

	@Inject
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	Address add(@PathVariable long uid, @PathVariable long mid, @RequestBody Address address) {
		address.setMid(mid);
		return userService.add(address);
	}

	@RequestMapping(value = "/{address_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long uid, @PathVariable long mid, @PathVariable int address_id) {
		userService.deleteAddress(uid, mid, address_id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Address> list(@PathVariable long uid, @PathVariable long mid) {
		return userService.listAddress(mid);
	}

	@RequestMapping(value = "/{address_id}", method = RequestMethod.POST)
	public @ResponseBody
	Address update(@PathVariable long uid, @PathVariable long mid, @PathVariable long address_id,
			@RequestBody Address address) {
		address.setAddress_id(address_id).setMid(mid);
		return userService.update(address);
	}

}
