package com.hd.project.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.project.model.MailRecord;
import com.hd.project.service.MailRecordService;

public class SaveMailProcessor implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaveMailProcessor.class);

	private MailRecordService mailRecordService;

	private String mail;

	public SaveMailProcessor(MailRecordService mailRecordService, String mail) {
		this.mailRecordService = mailRecordService;
		this.mail = mail;
	}

	@Override
	public void run() {
		LOGGER.info("{} icin kaydetme islemi basladi", mail);

		try {
			List<MailRecord> mailRecords = mailRecordService.findByMail(mail);

			if (mailRecords.isEmpty()) {
				MailRecord mailRecord = new MailRecord();
				mailRecord.setMail(mail);

				mailRecordService.save(mailRecord);
			}

			LOGGER.info("{} icin kaydetme islemi tamamlandi", mail);
		} catch (Exception e) {
			LOGGER.info("{} icin kaydetme islemi hata aldi!!!", mail);
			e.printStackTrace();
		}
	}

}
