package com.eason.spring.mvc.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Skill {

	@NotNull
	private String name;
	
	@NotNull
	private String desc;

	@Min(1)
	private int level;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLevel() {
	    return level;
    }

	public void setLevel(int level) {
	    this.level = level;
    }
	
	
}
