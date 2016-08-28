/**
 * 
 */
package com.mailsender.core;

import com.mailsender.core.message.SendEmailAck;
import com.mailsender.core.message.SendEmailRequest;
import com.mailsender.core.utils.JsonToObject;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author harsha
 *
 */
public class TestJsonToObject extends TestCase {

	private String sendMailReqJson;
	private String sendMailAckJson;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		sendMailAckJson = "{\r\n  \"requestId\" : \"1\",\r\n  \"status\" : \"OK\"\r\n}";
		sendMailReqJson = "{\r\n  \"requestId\" : \"1\",\r\n  \"senderName\" : \"Test Name\",\r\n  \"recipientEmail\" : \"harsha@test.com\",\r\n  \"subject\" : \"Test Subject\",\r\n  \"messageText\" : \"Test Msg\"\r\n}";
	}

	public void testSendMailReq() throws Exception {

		JsonToObject<SendEmailRequest> jsonToObject = new JsonToObject<SendEmailRequest>();
		SendEmailRequest sendEmailRequest = jsonToObject.converToObject(
				sendMailReqJson, SendEmailRequest.class);

		Assert.assertNotNull(sendEmailRequest);
		Assert.assertEquals("1", sendEmailRequest.getRequestId());
		Assert.assertEquals("Test Name", sendEmailRequest.getSenderName());
		Assert.assertEquals("harsha@test.com",
				sendEmailRequest.getRecipientEmail());
		Assert.assertEquals("Test Subject", sendEmailRequest.getSubject());
		Assert.assertEquals("Test Msg", sendEmailRequest.getMessageText());

	}

	public void testSendMailAck() throws Exception {

		JsonToObject<SendEmailAck> jsonToObject = new JsonToObject<SendEmailAck>();
		SendEmailAck sendEmailAck = jsonToObject.converToObject(
				sendMailAckJson, SendEmailAck.class);

		Assert.assertNotNull(sendEmailAck);
		Assert.assertEquals("1", sendEmailAck.getRequestId());
		Assert.assertEquals("OK", sendEmailAck.getStatus());
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
