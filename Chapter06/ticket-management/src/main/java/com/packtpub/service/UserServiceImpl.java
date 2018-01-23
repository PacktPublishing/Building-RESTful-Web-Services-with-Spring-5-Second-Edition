package com.packtpub.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.packtpub.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> getAllUsers() {
		return this.users;
	}

	@Override
	public User getUser(Integer userid) {		
		
		return users.stream()
		.filter(x -> x.getUserid()  == userid)
		.findAny()
		.orElse(new User(0, "Not Available"));
		
	}

	@Override
	public void createUser(Integer userid, String username) {
		User user = new User(userid, username);
		this.users.add(user);
	}

	@Override
	public void updateUser(Integer userid, String username) {
		users.stream()
				.filter(x -> x.getUserid()  == userid)
				.findAny()
				.orElseThrow(() -> new RuntimeException("Item not found"))
				.setUsername(username);		
	}
	
	@Override
	public void deleteUser(Integer userid) {   
		
		users.removeIf((User u) -> u.getUserid() == userid);
		
	}

	// Dummy users
	public static List<User> users;

	public UserServiceImpl() {
		users = new LinkedList<>();
		users.add(new User(100, "David"));
		users.add(new User(101, "Peter"));
		users.add(new User(102, "John"));
	}
}