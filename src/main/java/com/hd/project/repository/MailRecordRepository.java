package com.hd.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hd.project.model.MailRecord;

@Repository
public interface MailRecordRepository extends CrudRepository<MailRecord, Long> {

	List<MailRecord> findByActive(boolean active);

	List<MailRecord> findByMail(String mail);
}
