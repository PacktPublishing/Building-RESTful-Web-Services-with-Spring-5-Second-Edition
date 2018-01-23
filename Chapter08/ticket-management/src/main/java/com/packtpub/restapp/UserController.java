package com.packtpub.restapp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.packtpub.model.User;
import com.packtpub.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userSevice;

	@ResponseBody
	@RequestMapping("")
	public List<User> getAllUsers() {
		return userSevice.getAllUsers();
	}

	@ResponseBody
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id, WebRequest webRequest) {
		User user = userSevice.getUser(id);
		long updated = user.getUpdatedDate().getTime();
		boolean isNotModified = webRequest.checkNotModified(updated);
		logger.info("{getUser} isNotModified : " + isNotModified);
		if (isNotModified) {
			logger.info("{getUser} resource not modified since last call, so exiting");
			return null;
		}
		logger.info("{getUser} resource modified since last call, so get the updated content");
		return userSevice.getUser(id);
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> createUser(@RequestParam(value = "userid") Integer userid,
			@RequestParam(value = "username") String username) {
		Map<String, Object> map = new LinkedHashMap<>();
		userSevice.createUser(userid, username);
		map.put("result", "added");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Map<String, Object> updateUser(@RequestParam(value = "userid") Integer userid,
			@RequestParam(value = "username") String username) {
		Map<String, Object> map = new LinkedHashMap<>();
		userSevice.updateUser(userid, username);
		map.put("result", "updated");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(@PathVariable("id") Integer userid) {
		Map<String, Object> map = new LinkedHashMap<>();
		userSevice.deleteUser(userid);
		map.put("result", "deleted");
		return map;
	}
}