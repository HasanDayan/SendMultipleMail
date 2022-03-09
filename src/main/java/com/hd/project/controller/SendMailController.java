package com.hd.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hd.project.service.MailPropertiesService;
import com.hd.project.service.MailRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hd.project.batch.SendMailProcessor;
import com.hd.project.dto.EmailDTO;
import com.hd.project.model.MailRecord;

@Controller
public class SendMailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailController.class);

	private final MailRecordService mailRecordService;

	private final MailPropertiesService mailPropertiesService;

	public SendMailController(MailRecordService mailRecordService, MailPropertiesService mailPropertiesService) {
		this.mailRecordService = mailRecordService;
		this.mailPropertiesService = mailPropertiesService;
	}

	@GetMapping("/epostaGonder")
	public String getSendMail(Model model) {
		List<String> mailList = new ArrayList<>();

		for (MailRecord mailRecord : mailRecordService.findByActive(true)) {
			mailList.add(mailRecord.getMail());
		}

		model.addAttribute("mailList", mailList);
		return "send_mail";
	}

	@PostMapping("/epostaGonder")
	public String postSendMail(@ModelAttribute("emailDTO") EmailDTO emailDTO, RedirectAttributes redirectAttributes) {

		String returnURL = "redirect:/epostaGonder";
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			
			if ( emailDTO.getMail() == null || emailDTO.getMail().equals("all") ) {
				
				for (MailRecord mailRecord : mailRecordService.findByActive(true)) {
					SendMailProcessor processor = new SendMailProcessor(mailPropertiesService, mailRecord.getMail(), emailDTO);
					
					executorService.submit(processor);
				}
			} else {
				SendMailProcessor processor = new SendMailProcessor(mailPropertiesService, emailDTO.getMail(), emailDTO);

				executorService.submit(processor);
			}

			executorService.shutdown();

			LOGGER.info("E-posta gonderimi basariyla baslatildi");
			redirectAttributes.addFlashAttribute("message", "Success");
		} catch (Exception e) {
			LOGGER.error("E-posta gonderimi baslatilirken bir hata olustu");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed");
		}

		return returnURL;
	}

}
