package com.tistory.ospace.simplesecurity2.entity;

import org.apache.ibatis.type.Alias;

@Alias("Group")
public class Group {
	private Integer id;
	private String group_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	public String toString() {
		return "id["+id+"] group_name["+group_name+"]";
	}
}
