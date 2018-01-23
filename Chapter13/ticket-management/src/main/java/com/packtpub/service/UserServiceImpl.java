package com.packtpub.service;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packtpub.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	SecurityService securityService;

	@Override
	public List<User> getAllUsers() {
		return this.users;
	}

	@Override
	public User getUser(Integer userid) {		
		
		return users.stream()
		.filter(x -> x.getUserid().intValue()  == userid.intValue())
		.findAny()
		.orElse(null);		
	}	
	
	@Override
	public User getUser(String username, String password, Integer usertype) {
		
		//System.out.println("username["+username+"], password["+password+"], usertype["+usertype+"]");
		
		//System.out.println("users : "+users);
		
		//System.out.println("users.stream : "+users.stream());
		
		return users.stream()
		.filter(x -> x.getUsername().equalsIgnoreCase(username) && x.getPassword().equalsIgnoreCase(password)  && x.getUsertype() == usertype )
		.findAny()
		.orElse(null);
		
		//System.out.println("{getUser} user : "+user);
		
		//return user;
	}
	
	@Override
	public User getUserByToken(String token){
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(SecurityServiceImpl.secretKey))
			       .parseClaimsJws(token).getBody();
		
		if(claims == null || claims.getSubject() == null){
			return null;
		}
		
		String subject = claims.getSubject();
		
		if(subject.split("=").length != 2){
			return null;
		}
		
		String[] subjectParts = subject.split("=");
		
		
		Integer userid = new Integer(subjectParts[0]);
		Integer usertype = new Integer(subjectParts[1]);		
		
		System.out.println("{getUserByToken} usertype["+usertype+"], userid["+userid+"]");
		
		return new User(userid, usertype);
	}

	@Override
	public void createUser(Integer userid, String username, Integer usertype) {
		User user = new User(userid, username, 2);
		this.users.add(user);
	}
	
	@Override
	public void createUser(String username, String password, Integer usertype){
		User user = new User(username, password, usertype);		
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
		
		
		users.add(new User("sammy", "pass", 3)); // 3 - admin
		users.add(new User("chloe", "pass", 2)); // // 2 - CSR
		
		users.add(new User("peter", "pass", 1)); //  1 - general user
		users.add(new User("kevin", "pass", 1)); //  1 - general user
				  
	}
}