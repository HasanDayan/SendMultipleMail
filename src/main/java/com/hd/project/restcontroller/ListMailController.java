package com.hd.project.restcontroller;

import com.hd.project.service.MailRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hd.project.model.MailRecord;

@RestController
public class ListMailController {

	private final MailRecordService mailRecordService;

	public ListMailController(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	@GetMapping("/epostaListele")
	public Iterable<MailRecord> getListMail() {
		return mailRecordService.findAll();
	}

}
