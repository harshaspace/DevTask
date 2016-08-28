/**
 * 
 */
package com.mailsender.core.smtp;

import com.mailsender.core.message.SendEmailAck;
import com.mailsender.core.message.SendEmailRequest;

/**
 * @author harsha
 *
 */
public interface Convertable {

	
	SendEmailRequest converToObject(String request)throws Exception;
	String convertToJson(SendEmailAck sendEmailAck) throws Exception;
}
