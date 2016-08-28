package com.mailsender.core;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.mailsender.core.message.SendEmailAck;
import com.mailsender.core.message.SendEmailRequest;
import com.mailsender.core.message.enums.EmailAckStatus;
import com.mailsender.core.utils.ObjectToJson;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestObjToJson extends TestCase {

	private SendEmailRequest sendEmailRequest;
	private SendEmailAck sendEmailAck;
	private static Logger logger = Logger.getLogger(TestObjToJson.class);

	protected void setUp() throws Exception {

		sendEmailRequest = new SendEmailRequest();
		sendEmailRequest.setMessageText("Test Msg");
		sendEmailRequest.setRecipientEmail("harsha@test.com");
		sendEmailRequest.setRequestId(String.valueOf(1));
		sendEmailRequest.setSenderName("Test Name");
		sendEmailRequest.setSubject("Test Subject");

		sendEmailAck = new SendEmailAck();
		sendEmailAck.setRequestId(sendEmailRequest.getRequestId());
		sendEmailAck.setStatus(EmailAckStatus.OK.name());
	}

	public void testSendEmailAck() throws Exception {
		ObjectToJson<SendEmailAck> objectToJson = new ObjectToJson<SendEmailAck>();
		String actualJson = objectToJson.convertToJson(sendEmailAck);
		Assert.assertNotNull(actualJson, "Json String is null !");

	}
	
	public void testSendEmailRequest() throws Exception {
		ObjectToJson<SendEmailRequest> objectToJson = new ObjectToJson<SendEmailRequest>();
		String actualJson = objectToJson.convertToJson(sendEmailRequest);
		Assert.assertNotNull(actualJson, "Json String is null !");
	}

	protected void tearDown() throws Exception {
		sendEmailRequest = null;
		sendEmailAck = null;
	}

}
