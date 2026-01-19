package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	@GetMapping("/mypage/mypage_main")
	public String admin_main(Model model)
	{
		model.addAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
		model.addAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "main/main";
	}
}
