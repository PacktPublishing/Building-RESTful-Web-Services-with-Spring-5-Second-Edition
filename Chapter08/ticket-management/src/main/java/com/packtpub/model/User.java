package com.packtpub.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public User() {
	}

	private Integer userid;
	private String username;
	private Date updatedDate;

	public User(Integer userid, String username) {
		this.userid = userid;
		this.username = username;
	}

	public User(Integer userid, String username, Date updatedDate) {
		this.userid = userid;
		this.username = username;
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", updatedDate=" + updatedDate + "]";
	}
}