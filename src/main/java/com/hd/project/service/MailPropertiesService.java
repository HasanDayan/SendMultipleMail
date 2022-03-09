package com.hd.project.service;

import com.hd.project.util.CrudImplements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.MailProperties;
import com.hd.project.repository.MailPropertiesRepository;

import java.io.Serializable;

@Service
public class MailPropertiesService implements CrudImplements<MailProperties> {

	private final MailPropertiesRepository mailPropertiesRepository;

	public MailPropertiesService(MailPropertiesRepository mailPropertiesRepository) {
		this.mailPropertiesRepository = mailPropertiesRepository;
	}

	@Override
	public JpaRepository<MailProperties, Serializable> getRepository() {
		return mailPropertiesRepository;
	}

}
