package com.hd.project.service;

import com.hd.project.model.MailRecord;
import com.hd.project.util.CrudImplements;

import java.util.List;

public interface MailRecordService extends CrudImplements<MailRecord> {

    List<MailRecord> findByActive(boolean active);

    List<MailRecord> findByMail(String mail);
}
