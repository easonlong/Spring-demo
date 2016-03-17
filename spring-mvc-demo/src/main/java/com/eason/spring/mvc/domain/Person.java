package com.eason.spring.mvc.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.eason.spring.mvc.validation.constraint.NonZero;
import com.eason.spring.mvc.validation.constraint.Sex;
import com.eason.spring.mvc.validation.groups.GroupOne;
import com.eason.spring.mvc.validation.groups.GroupTwo;

public class Person {

	@NotBlank(message = "{person.name}", groups={GroupOne.class, GroupTwo.class})
	private String name;

	@Range(min = 1, max = 100, message = "{person.age}", groups={GroupOne.class, GroupTwo.class})
	private int age;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private Date birthday;

	@Sex(groups={GroupTwo.class})
	private String sex;

	@NotNull
	@Valid
	private Skill skill;

	@NonZero
	private int credit;
	
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String toString() {
		return name + "-" + age + "-" + sex;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
