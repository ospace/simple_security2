package com.tistory.ospace.simplesecurity2.entity;

import org.apache.ibatis.type.Alias;

@Alias("Notice")
public class Notice {
	private Integer id;
	private String content;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "{id:"+id+",content:\""+content+"\"}";
	}
}
