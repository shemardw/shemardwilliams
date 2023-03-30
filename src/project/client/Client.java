package project.client;

import org.apache.commons.lang3.StringUtils;

import java.awt.BorderLayout;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Client {
	private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;

    private ArrayList<UserStatusListener> userStatusListeners = new ArrayList<>();
    private ArrayList<MessageListener> messageListeners = new ArrayList<>();

    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }
	private static final Logger LOGGER = LogManager.getLogger(Client.class);

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 8888);
        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String userName) {
                System.out.println("ONLINE: " + userName);
                LOGGER.info("Use is Online");
            }

            @Override
            public void offline(String userName) {
                System.out.println("OFFLINE: " + userName);
                LOGGER.info("User is Offline");
            }
        });

        client.addMessageListener(new MessageListener() {
            @Override
            public void getMessage(String sender, String messageBody) {
                System.out.println("You got a message from " + sender + " ->" + messageBody);
                LOGGER.info("Unread Message");
            }
        });

        if (!client.connect()) {
            System.err.println("Connection failed.");
            LOGGER.error("Failed Connetion to Client");
        } else {
            System.out.println("Connection successful");

           
        }
    }

    public void message(String receiver, String messageBody) throws IOException {
        String command = "message " + receiver + " " + messageBody + "\n";
        serverOut.write(command.getBytes());
    }

    public boolean login(String userName, String password) throws IOException {
        String command = "login " + userName + " " + password + "\n";
        serverOut.write(command.getBytes());

        String response = bufferedIn.readLine();
        System.out.println("Response Line:" + response);
        LOGGER.info("Response Has been recieved");

        if ("ok login".equalsIgnoreCase(response)) {
            startMessageReader();
         
            return true;
        } else {
            return false;
        }
    }

    public void logoff() throws IOException {
        String command = "logoff\n";
        serverOut.write(command.getBytes());
    }

    private void startMessageReader() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                readMessage();
            }
        };
        thread.start();
    }

    private void readMessage() {
        try {
            String line;
            while ((line = bufferedIn.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String command = tokens[0];
                    if ("online".equalsIgnoreCase(command)) {
                        handleOnline(tokens);
                    } else if ("offline".equalsIgnoreCase(command)) {
                        handleOffline(tokens);
                    } else if ("message".equalsIgnoreCase(command)) {
                        String[] tokensMsg = StringUtils.split(line, null, 3);
                        handleMessage(tokensMsg);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("Something went Wrong.");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Something went Wrong.");
            }
        }
    }

    private void handleMessage(String[] tokensMsg) {
        String userName = tokensMsg[1];
        String messageBody = tokensMsg[2];

        for(MessageListener listener : messageListeners) {
            listener.getMessage(userName, messageBody);
        }
    }

    private void handleOffline(String[] tokens) {
        String userName = tokens[1];
        for(UserStatusListener listener : userStatusListeners) {
            listener.offline(userName);
        }
    }

    private void handleOnline(String[] tokens) {
        String userName = tokens[1];
        for(UserStatusListener listener : userStatusListeners) {
            listener.online(userName);
        }
    }

    public boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            LOGGER.info("Client port is " + socket.getLocalPort()); 
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Something went Wrong.");
        }
        return false;
    }

    public void addUserStatusListener(UserStatusListener listener) {
        userStatusListeners.add(listener);
    }

    public void removeUserStatusListener(UserStatusListener listener) {
        userStatusListeners.remove(listener);
    }

    public void addMessageListener(MessageListener listener) {
        messageListeners.add(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageListeners.remove(listener);
    }

}
