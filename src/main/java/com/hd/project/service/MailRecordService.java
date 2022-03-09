package com.hd.project.service;

import java.io.Serializable;
import java.util.List;

import com.hd.project.util.CrudImplements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailRecord;
import com.hd.project.repository.MailRecordRepository;

@Service
public class MailRecordService implements CrudImplements<MailRecord> {

	private final MailRecordRepository mailRecordRepository;

	public MailRecordService(MailRecordRepository mailRecordRepository) {
		this.mailRecordRepository = mailRecordRepository;
	}

	public List<MailRecord> findByActive(boolean active) {
		return mailRecordRepository.findByActive(active);
	}

	public List<MailRecord> findByMail(String mail) {
		return mailRecordRepository.findByMail(mail);
	}

	@Override
	public JpaRepository<MailRecord, Serializable> getRepository() {
		return mailRecordRepository;
	}

}
