package com.hd.project.batch;

import java.util.List;

import com.hd.project.service.MailRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.project.model.MailRecord;

public class SaveMailProcessor implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaveMailProcessor.class);

	private final MailRecordService mailRecordService;

	private final String mail;

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
