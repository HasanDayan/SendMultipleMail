package com.hd.project.controller;

import java.util.List;

import com.hd.project.service.MailPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hd.project.dto.MailPropertiesDTO;
import com.hd.project.model.MailProperties;

@Controller
public class SenderMailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SenderMailController.class);

	private final MailPropertiesService mailPropertiesService;

	public SenderMailController(MailPropertiesService mailPropertiesService) {
		this.mailPropertiesService = mailPropertiesService;
	}

	@GetMapping("/epostaBilgileri")
	public String getSenderMail(Model model) {
		List<MailProperties> mailProperties = mailPropertiesService.findAll();

		if (mailProperties.isEmpty()) {
			model.addAttribute("mailPropertiesDTO", new MailPropertiesDTO());
		} else {
			MailProperties property = mailProperties.get(0);

			MailPropertiesDTO mailPropertiesDTO = new MailPropertiesDTO();
			mailPropertiesDTO.setId(property.getId());
			mailPropertiesDTO.setFrom(property.getSenderMail());
			mailPropertiesDTO.setHost(property.getSmtpHost());
			mailPropertiesDTO.setPassword(property.getSenderMailPassword());
			mailPropertiesDTO.setPort(property.getSmtpPort());

			model.addAttribute("mailPropertiesDTO", mailPropertiesDTO);
		}

		return "mail_info";
	}

	@PostMapping("/epostaBilgileri")
	public String postSenderMail(@ModelAttribute("mailPropertiesDTO") MailPropertiesDTO mailPropertiesDTO,
			RedirectAttributes redirectAttributes) {

		String returnURL = "/epostaBilgileri";
		try {
			MailProperties properties = new MailProperties();

			properties.setId(mailPropertiesDTO.getId());
			properties.setSenderMail(mailPropertiesDTO.getFrom());
			properties.setSmtpHost(mailPropertiesDTO.getHost());
			
			String pass = mailPropertiesDTO.getPassword();
			if (pass != null && !pass.equals(""))
				properties.setSenderMailPassword(pass);
			
			properties.setSmtpPort(mailPropertiesDTO.getPort());

			mailPropertiesService.save(properties);

			LOGGER.info("Bilgiler güncellendi");
			redirectAttributes.addFlashAttribute("message", "Success");
		} catch (Exception e) {
			LOGGER.error("Bilgiler güncellenirken bir hata olustu");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed");
		}

		return returnURL;
	}

}
