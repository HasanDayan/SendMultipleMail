package com.hd.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailProperties;
import com.hd.project.repository.MailPropertiesRepository;
import com.hd.project.service.MailPropertiesService;

@Service
public class MailPropertiesServiceImpl implements MailPropertiesService {

	@Autowired
	private MailPropertiesRepository mailPropertiesRepository;

	@Override
	public CrudRepository<MailProperties, Long> getRepository() {
		return mailPropertiesRepository;
	}

}
