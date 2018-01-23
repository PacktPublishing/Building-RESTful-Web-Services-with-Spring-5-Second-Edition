package com.packtpub.restapp.ticketmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.packtpub.model.User;
import com.packtpub.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	UserService userSevice;

	@Test
	public void testAllUsers(){
		List<User> users = userSevice.getAllUsers();		
		assertEquals(3, users.size());
	}
	
	@Test
	public void testSingleUser(){
		User user = userSevice.getUser(100);		
		assertTrue(user.getUsername().contains("David"));
	}
}
