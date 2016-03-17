package com.eason.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.domain.Person;
import com.eason.spring.mvc.validation.groups.GroupOne;
import com.eason.spring.mvc.validation.groups.GroupTwo;

@Controller
@RequestMapping(value="/person")
public class PersonController extends BaseController{
	@RequestMapping(value="/add", method=RequestMethod.GET)
	@ResponseBody
	public String addPerson(@Validated({GroupOne.class, GroupTwo.class}) Person person){
		return person.toString();
	}
}
