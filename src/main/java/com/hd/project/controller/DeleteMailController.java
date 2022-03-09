package com.hd.project.controller;

import java.util.Optional;

import com.hd.project.service.MailRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hd.project.model.MailRecord;

@Controller
public class DeleteMailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMailController.class);

	private final MailRecordService mailRecordService;

	public DeleteMailController(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	@GetMapping("/epostaSil/{id}")
	public String getAddMail(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			Optional<MailRecord> optionalMailRecord = mailRecordService.findById(id);

			if (optionalMailRecord.isPresent()) {
				mailRecordService.deleteById(id);

				LOGGER.info("E-posta adresi basariyla silindi : {}", optionalMailRecord.get().getMail());
				redirectAttributes.addFlashAttribute("message", "Success");
			}
		} catch (Exception e) {
			LOGGER.error("E-posta adresi silinirken bir hata olustu");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed");
		}

		return "redirect:/epostaIslemleri";
	}

}
