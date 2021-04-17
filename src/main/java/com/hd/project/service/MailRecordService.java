package com.hd.project.service;

import java.util.List;

import com.hd.project.model.MailRecord;
import com.hd.project.util.CrudImplements;

public interface MailRecordService extends CrudImplements<MailRecord> {

	List<MailRecord> findByActive(boolean active);

	List<MailRecord> findByMail(String mail);

}
