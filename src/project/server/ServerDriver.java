package project.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerDriver {
	private static final Logger LOGGER = LogManager.getLogger(ServerDriver.class);
	public static void main(String[] args) {
		int portNumber = 8888;
		Server server = new Server(portNumber);
		server.start();
	}	
}
