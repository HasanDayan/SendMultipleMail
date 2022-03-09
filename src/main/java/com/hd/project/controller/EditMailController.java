package com.hd.project.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import com.hd.project.service.MailRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hd.project.dto.MailRecordDTO;
import com.hd.project.model.MailRecord;

@Controller
public class EditMailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EditMailController.class);

	private final MailRecordService mailRecordService;

	public EditMailController(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	@GetMapping("/epostaDuzenle/{id}")
	public String getAddMail(@PathVariable("id") Long id, Model model) {

		Optional<MailRecord> optionalMailRecord = mailRecordService.findById(id);

		if (optionalMailRecord.isPresent()) {
			MailRecord mailRecord = optionalMailRecord.get();

			MailRecordDTO mailRecordDTO = new MailRecordDTO();
			mailRecordDTO.setId(mailRecord.getId());
			mailRecordDTO.setMail(mailRecord.getMail());
			mailRecordDTO.setActive(mailRecord.isActive());

			model.addAttribute("mailRecordDTO", mailRecordDTO);
		} else
			model.addAttribute("mailRecordDTO", new MailRecordDTO());

		return "edit_mail";
	}

	@PostMapping("/epostaDuzenle")
	public String postAddMail(@ModelAttribute("mailRecordDTO") MailRecordDTO mailRecordDTO,
			RedirectAttributes redirectAttributes) {
		try {
			Optional<MailRecord> optionalMailRecord = mailRecordService.findById(mailRecordDTO.getId());

			if (optionalMailRecord.isPresent()) {
				MailRecord mailRecord = optionalMailRecord.get();
				mailRecord.setMail(mailRecordDTO.getMail());
				mailRecord.setActive(mailRecordDTO.isActive());
				mailRecord.setUpdateDate(LocalDateTime.now());

				mailRecordService.save(mailRecord);
			}
			
			LOGGER.info("E-posta adresi basariyla guncellendi : {}", mailRecordDTO.getMail());
			redirectAttributes.addFlashAttribute("message", "Success");
		} catch (Exception e) {
			LOGGER.error("E-posta adresi guncellenirken bir hata olustu : {}", mailRecordDTO.getMail());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed");
		}

		return "redirect:/epostaIslemleri";
	}

}
