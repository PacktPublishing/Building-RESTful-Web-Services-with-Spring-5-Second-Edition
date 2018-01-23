package com.packtpub.model;

import java.io.Serializable;

public class User implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3453281303625368221L;
	
	private Integer userid;
	
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

	private String username;
	
	public User(){
		
	}

	public User(Integer userid, String username) {
		this.userid = userid;
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + "]";
	}
}
