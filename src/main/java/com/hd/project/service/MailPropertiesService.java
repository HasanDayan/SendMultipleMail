package com.hd.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailProperties;
import com.hd.project.repository.MailPropertiesRepository;

@Service
public class MailPropertiesService {

	@Autowired
	private MailPropertiesRepository mailPropertiesRepository;

	public Iterable<MailProperties> findAll() {
		return mailPropertiesRepository.findAll();
	}

	public MailProperties save(MailProperties mailProperties) {
		return mailPropertiesRepository.save(mailProperties);
	}

}
