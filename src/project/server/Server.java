package project.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Server extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private final int serverPort;
    private ArrayList<ServerHandler> workerList = new ArrayList<>();

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    public List<ServerHandler> getHandlerList() {
        return workerList;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while(true) {
                System.out.println("About to accept client connection...");
                Socket clientSocket = serverSocket.accept();
                LOGGER.info("Accepted connection from " + clientSocket);
                ServerHandler worker = new ServerHandler(this, clientSocket);
                workerList.add(worker);
                worker.start();
                LOGGER.info("Connection made successfully.");
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Error occurred during connection.");
        }
    }

    public void removeWorker(ServerHandler serverWorker) {
        workerList.remove(serverWorker);
    }
}
