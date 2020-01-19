package com.tim04.school.trivia.controllers;

import java.security.Principal;

import org.springframework.boot.actuate.trace.http.HttpTrace.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//@RequestMapping(value = "/login", method = RequestMethod.GET)
	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		return "loginPage";
	}


}
