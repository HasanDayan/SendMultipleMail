package com.hd.project.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hd.project.model.MailRecord;

@Repository
public interface MailRecordRepository extends JpaRepository<MailRecord, Serializable> {

	List<MailRecord> findByActive(boolean active);

	List<MailRecord> findByMail(String mail);
}
