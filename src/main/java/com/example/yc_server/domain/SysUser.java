package com.example.yc_server.domain;



import javax.persistence.*;

@Entity
public class SysUser { //1
	
	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	private String password;
	private String roles;

	public boolean isEnabled() {
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
