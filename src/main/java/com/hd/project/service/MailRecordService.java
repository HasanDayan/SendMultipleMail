package com.hd.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailRecord;
import com.hd.project.repository.MailRecordRepository;

@Service
public class MailRecordService {

	@Autowired
	private MailRecordRepository mailRecordRepository;

	public Iterable<MailRecord> findAll() {
		return mailRecordRepository.findAll();
	}

	public List<MailRecord> findByActive(boolean active) {
		return mailRecordRepository.findByActive(active);
	}

	public Optional<MailRecord> findById(Long id) {
		return mailRecordRepository.findById(id);
	}

	public void save(MailRecord mailRecord) {
		mailRecordRepository.save(mailRecord);
	}

	public void deleteById(Long id) {
		mailRecordRepository.deleteById(id);
	}

	public List<MailRecord> findByMail(String mail) {
		return mailRecordRepository.findByMail(mail);
	}

}
