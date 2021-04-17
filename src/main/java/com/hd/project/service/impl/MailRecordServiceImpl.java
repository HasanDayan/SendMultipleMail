package com.hd.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailRecord;
import com.hd.project.repository.MailRecordRepository;
import com.hd.project.service.MailRecordService;

@Service
public class MailRecordServiceImpl implements MailRecordService {

	@Autowired
	private MailRecordRepository mailRecordRepository;

	@Override
	public List<MailRecord> findByActive(boolean active) {
		return mailRecordRepository.findByActive(active);
	}

	@Override
	public List<MailRecord> findByMail(String mail) {
		return mailRecordRepository.findByMail(mail);
	}

	@Override
	public CrudRepository<MailRecord, Long> getRepository() {
		return mailRecordRepository;
	}

}
