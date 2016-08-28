/**
 * 
 */
package com.mailsender.core.utils;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailsender.core.message.SendEmailRequest;

/**
 * @author harsha
 *
 */
public class ObjectToJson<T> {
	
	/**
	 * This method converts given object to a json string 
	 * @param t
	 * @throws Exception
	 * **/
	public String convertToJson(T t) throws Exception{		
		ObjectMapper objectMapper=new ObjectMapper();		
		String jsonString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);		
		return jsonString;		
	}
}
