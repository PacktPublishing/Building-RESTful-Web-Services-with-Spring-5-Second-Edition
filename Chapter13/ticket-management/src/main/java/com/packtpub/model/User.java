package com.packtpub.model;

public class User {	

	private Integer userid;
	
	private String username;
	
	private String password;
	
	/*
	 * usertype:
	 * 		1 - general user
	 * 		2 - CSR (Customer Service Representative)
	 * 		3 - admin
	 * 		
	 * 
	 */
	private Integer usertype;
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsertype(Integer usertype){
		this.usertype = usertype;
	}
	
	public Integer getUsertype(){
		return this.usertype;
	}
	
	private static Integer userCounter = 100;
	
	public User(Integer userid, String username, Integer usertype) {		
		
		this.userid = userid;
		this.username = username;
		this.usertype = usertype;
	}
	
	public User(){
		
	}
	
	public User(Integer userid, Integer usertype) {
		this.userid = userid;
		this.usertype = usertype;
	}
	
	public User(String username, Integer usertype) {
		userCounter++;
		
		this.userid = userCounter;
		this.username = username;
		this.usertype = usertype;
	}
	
	public User(String username, String password, Integer usertype) {
		userCounter++;
		
		this.userid = userCounter;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", usertpye = "+usertype+"]";
	}
}
