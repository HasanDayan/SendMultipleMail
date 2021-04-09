package com.hd.project.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hd.project.model.MailRecord;
import com.hd.project.service.MailRecordService;

@RestController
public class ListMailController {

	@Autowired
	private MailRecordService mailRecordService;

	@GetMapping("/epostaListele")
	public Iterable<MailRecord> getListMail() {
		return mailRecordService.findAll();
	}

}
