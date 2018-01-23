package com.packtpub.reactive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private Integer userid;
	private String username;

	public User(@JsonProperty("userid") Integer userid, @JsonProperty("username") String username){
		this.userid = userid;
		this.username = username;
	}

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
}