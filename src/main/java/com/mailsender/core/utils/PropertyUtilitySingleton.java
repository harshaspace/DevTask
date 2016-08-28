/**
 * 
 */
package com.mailsender.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author harsha
 *
 */
public class PropertyUtilitySingleton {

	private static Logger logger = Logger
			.getLogger(PropertyUtilitySingleton.class);
	
	private static PropertyUtilitySingleton propertyUtilitySingleton;
	private Properties properties;

	private PropertyUtilitySingleton() {
		properties = new Properties();
		InputStream input = null;
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			input = new FileInputStream(classLoader.getResource(
					"serverconfig.properties").getFile());
			properties.load(input);
		} catch (IOException e) {
			logger.error("::: Error in load prps ::: ", e);
		}
		logger.info("::: PropertyUtilitySingleton initiated :::");
	}
	

	/**
	 * This method retuns a singleton instance of the class
	 * **/
	public static PropertyUtilitySingleton getInstance() {
		if (propertyUtilitySingleton == null) {
			synchronized (PropertyUtilitySingleton.class) {
				if (propertyUtilitySingleton == null) {
					propertyUtilitySingleton = new PropertyUtilitySingleton();
				}
			}
		}
		return propertyUtilitySingleton;
	}

	public Properties getProperties() {
		return properties;
	}
}
