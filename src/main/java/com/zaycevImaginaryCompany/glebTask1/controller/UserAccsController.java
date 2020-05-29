package com.zaycevImaginaryCompany.glebTask1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.service.AccountService;

@Controller
public class UserAccsController
{
	@Autowired
	private AccountService aService;
	
	@PostMapping("/userAddAccount")
	public String addAccountToUSer(@Valid User user, Model model)
	{
		System.out.println(user);
		return "startpage";
	}
}
