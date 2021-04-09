package com.hd.project.batch;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.project.dto.EmailDTO;
import com.hd.project.model.MailProperties;
import com.hd.project.service.MailPropertiesService;

public class SendMailProcessor implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailProcessor.class);
	
	private static final String ERROR_LOG_FORMAT = "{} icin mail gonderilemedi";

	private MailPropertiesService mailPropertiesService;

	private String mail;

	private EmailDTO emailDTO;

	public SendMailProcessor(MailPropertiesService mailPropertiesService, String mail, EmailDTO emailDTO) {
		this.mailPropertiesService = mailPropertiesService;
		this.mail = mail;
		this.emailDTO = emailDTO;
	}

	@Override
	public void run() {
		LOGGER.info("{} icin mail gonderiliyor...\n", mail);

		List<MailProperties> mailProperties = (List<MailProperties>) mailPropertiesService.findAll();

		MailProperties property = null;

		if (mailProperties.isEmpty()) {
			property = new MailProperties();
		} else {
			property = mailProperties.get(0);
		}

		Session session = Session.getInstance(getProperties(property), null);
		session.setDebug(false);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(property.getSenderMail()));
			InternetAddress toAddress = new InternetAddress(mail);
			message.addRecipient(Message.RecipientType.TO, toAddress);

			message.setSubject(emailDTO.getSubject(), "UTF-8");
			message.setContent(emailDTO.getContent(), "text/html; charset=utf-8");

			Transport transport = session.getTransport("smtp");
			transport.connect(property.getSmtpHost(), property.getSenderMail(), property.getSenderMailPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			LOGGER.info("{} icin mail gonderildi!!!\n", mail);
		} catch (AddressException ae) {
			LOGGER.error(ERROR_LOG_FORMAT, mail);
			ae.printStackTrace();
		} catch (MessagingException me) {
			LOGGER.error(ERROR_LOG_FORMAT, mail);
			me.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(ERROR_LOG_FORMAT, mail);
			e.printStackTrace();
		}

	}

	private Properties getProperties(MailProperties properties) {
		Properties props = System.getProperties();

		props.put("mail.smtp.host", properties.getSmtpHost());
		props.put("mail.smtp.user", properties.getSenderMail());
		props.put("mail.smtp.password", properties.getSenderMailPassword());
		props.put("mail.smtp.port", properties.getSmtpPort());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl.checkserveridentity", "true");
		props.put("mail.debug", "false");
		
		return props;
	}

}
