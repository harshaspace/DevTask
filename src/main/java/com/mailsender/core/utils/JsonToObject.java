/**
 * 
 */
package com.mailsender.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author harsha
 *
 */
public class JsonToObject<T> {

	
	/**
	 * This method converts given json string 
	 * to a given object type.
	 * @param jsonString
	 * @param valueType
	 * @throws Exception
	 * **/
	public T converToObject(String jsonString,Class<T> valueType) throws Exception{		
		ObjectMapper objectMapper = new ObjectMapper();
		return (T) objectMapper.readValue(jsonString,valueType);
	}
	
}
