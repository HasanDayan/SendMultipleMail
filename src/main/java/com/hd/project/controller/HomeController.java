package com.hd.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private String applicationName = "Toplu E-posta Gönderimi Sistemine Hoşgeldiniz";

	@GetMapping("/")
	public String getHmePage(Model model) {
		model.addAttribute("appName", applicationName);
		return "home";
	}
}
