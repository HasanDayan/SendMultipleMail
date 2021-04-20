package com.hd.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hd.project.model.SentMail;

@Repository
public interface SentMailRepository extends CrudRepository<SentMail, Long> {

}
