/**
 * 
 */
package com.mailsender.core.skeleton;

import java.net.*;
import java.io.*;

import org.apache.log4j.Logger;

import com.mailsender.core.message.SendEmailAck;
import com.mailsender.core.message.SendEmailRequest;
import com.mailsender.core.message.enums.EmailAckStatus;
import com.mailsender.core.smtp.Convertable;
import com.mailsender.core.smtp.MailSender;
import com.mailsender.core.utils.JsonToObject;
import com.mailsender.core.utils.ObjectToJson;

/**
 * @author harsha
 *
 */
public class ClientWorker implements Runnable,Convertable {

	private static final Logger logger = Logger.getLogger(ClientWorker.class);
	private Socket socket;
	private MailSender mailSender;

	public ClientWorker(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String clientRequest = "";
		mailSender = new MailSender();
		logger.info("Accepted Client ; Address - "
				+ socket.getInetAddress().getHostName());
		SendEmailAck sendEmailAck = new SendEmailAck();
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			clientRequest = in.readUTF();
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());

			logger.debug(clientRequest);
			// converts the client Json request to object & set it to mailSender
			// runnable obj.
			mailSender.setSendEmailRequest(converToObject(clientRequest));
			sendEmailAck.setRequestId(mailSender.getSendEmailRequest()
					.getRequestId());
			sendEmailAck.setStatus(EmailAckStatus.OK.name());
			// send status to email client app.
			out.writeUTF(convertToJson(sendEmailAck));

			// call SMTP server with a separate thread
			Thread smtpOperationThread = new Thread(mailSender);
			smtpOperationThread.start();

		} catch (Exception e) {
			sendEmailAck.setStatus(EmailAckStatus.ERROR.name());
			logger.error("Internal Server Error ", e);
		}
	}

	public String convertToJson(SendEmailAck sendEmailAck) throws Exception {
		ObjectToJson<SendEmailAck> objectToJson = new ObjectToJson<SendEmailAck>();
		String jsonString = objectToJson.convertToJson(sendEmailAck);
		return jsonString;
	}

	public SendEmailRequest converToObject(String request) throws Exception {
		JsonToObject<SendEmailRequest> jsonToObject = new JsonToObject<SendEmailRequest>();
		SendEmailRequest sendEmailRequest = jsonToObject.converToObject(
				request, SendEmailRequest.class);

		return sendEmailRequest;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			socket.close();
		} catch (Exception ignore) {
			logger.warn("Error in GC socket object :: ", ignore);
		}
	}

}
