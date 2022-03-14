package com.hd.project.service.impl;

import java.io.Serializable;
import java.util.List;

import com.hd.project.service.MailRecordService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailRecord;
import com.hd.project.repository.MailRecordRepository;

@Service
public class MailRecordServiceImpl implements MailRecordService {

	private final MailRecordRepository mailRecordRepository;

	public MailRecordServiceImpl(MailRecordRepository mailRecordRepository) {
		this.mailRecordRepository = mailRecordRepository;
	}

	@Override
	public List<MailRecord> findByActive(boolean active) {
		return mailRecordRepository.findByActive(active);
	}

	@Override
	public List<MailRecord> findByMail(String mail) {
		return mailRecordRepository.findByMail(mail);
	}

	@Override
	public JpaRepository<MailRecord, Serializable> getRepository() {
		return mailRecordRepository;
	}

}
