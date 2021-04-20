package com.hd.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.hd.project.model.SentMail;
import com.hd.project.repository.SentMailRepository;
import com.hd.project.service.SentMailService;

@Service
public class SentMailServiceImpl implements SentMailService {

	@Autowired
	private SentMailRepository sentMailRepository;

	@Override
	public CrudRepository<SentMail, Long> getRepository() {
		return sentMailRepository;
	}

}
