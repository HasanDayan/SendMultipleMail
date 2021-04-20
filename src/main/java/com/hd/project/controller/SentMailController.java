package com.hd.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hd.project.service.SentMailService;

@Controller
public class SentMailController {

	@Autowired
	private SentMailService sentMailService;

	@GetMapping("/gonderilenEpostalar")
	public String getSentMail(Model model) {
		model.addAttribute("sentMails", sentMailService.findAll());
		return "sent_mail";
	}

}
