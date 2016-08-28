/**
 * 
 */
package com.mailsender.core.smtp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.mailsender.core.message.SendEmailAck;
import com.mailsender.core.message.SendEmailRequest;
import com.mailsender.core.message.enums.EmailAckStatus;
import com.mailsender.core.utils.JsonToObject;
import com.mailsender.core.utils.ObjectToJson;
import com.mailsender.core.utils.PropertyUtilitySingleton;

/**
 * @author harsha
 *
 */
public class MailSender implements Runnable {

	private static Logger logger;
	private SendEmailRequest sendEmailRequest;

	public MailSender() {
		logger = Logger.getLogger(this.getClass());
	}

	private void sendEmail() throws Exception {

		String toAddress = this.sendEmailRequest.getRecipientEmail();
		String subject = this.sendEmailRequest.getSubject();
		String messageText = this.sendEmailRequest.getMessageText();

		final String username = PropertyUtilitySingleton.getInstance()
				.getProperties().getProperty("mail.username");
		final String password = PropertyUtilitySingleton.getInstance()
				.getProperties().getProperty("mail.password");

		Properties props = new Properties();
		props.put("mail.smtp.auth", PropertyUtilitySingleton.getInstance()
				.getProperties().getProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable",
				PropertyUtilitySingleton.getInstance().getProperties()
						.getProperty("mail.smtp.starttls.enable"));
		props.put("mail.smtp.host", PropertyUtilitySingleton.getInstance()
				.getProperties().getProperty("mail.smtp.host"));
		props.put("mail.smtp.port", PropertyUtilitySingleton.getInstance()
				.getProperties().getProperty("mail.smtp.port"));

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(PropertyUtilitySingleton
				.getInstance().getProperties().getProperty("mail.username")));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		message.setSubject(subject);
		message.setText(messageText);
		Transport.send(message);
		// no exceptions means; email sent successfully.

	}

	public void run() {

		try {
			sendEmail();
		} catch (Exception e) {
			logger.error(" Email Sending Failed | Request Id : ["
					+ this.sendEmailRequest.getRequestId() + "] ", e);
		}
	}

	public SendEmailRequest getSendEmailRequest() {
		return sendEmailRequest;
	}

	public void setSendEmailRequest(SendEmailRequest sendEmailRequest) {
		this.sendEmailRequest = sendEmailRequest;
	}

}
