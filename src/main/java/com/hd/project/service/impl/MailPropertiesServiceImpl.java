package com.hd.project.service.impl;

import com.hd.project.service.MailPropertiesService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailProperties;
import com.hd.project.repository.MailPropertiesRepository;

import java.io.Serializable;

@Service
public class MailPropertiesServiceImpl implements MailPropertiesService {

	private final MailPropertiesRepository mailPropertiesRepository;

	public MailPropertiesServiceImpl(MailPropertiesRepository mailPropertiesRepository) {
		this.mailPropertiesRepository = mailPropertiesRepository;
	}

	@Override
	public JpaRepository<MailProperties, Serializable> getRepository() {
		return mailPropertiesRepository;
	}

}
