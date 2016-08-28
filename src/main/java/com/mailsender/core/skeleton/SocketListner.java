/**
 * 
 */
package com.mailsender.core.skeleton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.mailsender.core.utils.PropertyUtilitySingleton;


/**
 * @author harsha
 *
 */
public class SocketListner {

	private static final Logger logger = Logger.getLogger(SocketListner.class);
	// static ServerSocket variable
	private static ServerSocket serverSocket;
	private int port = Integer.parseInt(PropertyUtilitySingleton.getInstance()
			.getProperties().getProperty("server.port"));

	public static void main(String args[]) throws IOException {
		SocketListner socketListner = new SocketListner();
		serverSocket = new ServerSocket(socketListner.port);

		logger.info("*********************************************");
		logger.info("*********	SERVER IS UP & RUNNING	**********");
		logger.info("*********************************************");

		while (true) {
			Socket clientSocket = serverSocket.accept();
			ClientWorker clientWorker = new ClientWorker(clientSocket);
			Thread clientSeviceThread = new Thread(clientWorker);
			clientSeviceThread.start();
		}
	}
}
