package com.tistory.ospace.simplesecurity2.entity;

import java.util.Collection;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class User {
	private Integer id;
	private String  username;
	private String  password;
	private boolean enabled;
	private Collection<Authority> authorities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public String toString() {
		return "username["+username+"] password["+password+"] enabled["+enabled+"]";
	}
}
