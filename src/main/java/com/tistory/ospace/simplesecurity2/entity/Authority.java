package com.tistory.ospace.simplesecurity2.entity;

import org.apache.ibatis.type.Alias;

@Alias("Authority")
public class Authority {
    private String username;
    private String authority;
    
    public static Authority of(String authority) {
    	Authority ret = new Authority();
    	ret.setAuthority(authority);
    	return ret;
    }
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    public String toString() {
		return "username["+username+"] authority["+authority+"]";
	}
}
