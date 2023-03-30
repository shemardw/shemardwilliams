package project.server;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class ServerHandler extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(ServerHandler.class);

	private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;
    private HashSet<String> groupSet = new HashSet<>();

    public ServerHandler(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = reader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String command = tokens[0];
                if ("logoff".equals(command) || "quit".equalsIgnoreCase(command)) {
                    handleLogoff();
                    break;
                } else if ("login".equalsIgnoreCase(command)) {
                    handleLogin(outputStream, tokens);
                } else if ("message".equalsIgnoreCase(command)) {
                    String[] tokensmessage = StringUtils.split(line, null, 3);
                    handleMessage(tokensmessage);
                } else if ("join".equalsIgnoreCase(command)) {
                    handleJoin(tokens);
                } else if ("leave".equalsIgnoreCase(command)) {
                    handleLeave(tokens);
                } else {
                    String message = "unknown '" + command + "'\n";
                    outputStream.write(message.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void handleLeave(String[] tokens) {
        if (tokens.length > 1) {
            String group = tokens[1];
            groupSet.remove(group);
            LOGGER.info("Left group");
        }
    }

    public boolean isMemberofGroup(String group) {
        return groupSet.contains(group);
    }

    private void handleJoin(String[] tokens) {
        if (tokens.length > 1) {
            String group = tokens[1];
            groupSet.add(group);
        }
    }

    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String body = tokens[2];

        boolean isgroup = sendTo.charAt(0) == '#';

        List<ServerHandler> workerList = server.getHandlerList();
        for(ServerHandler worker : workerList) {
            if (isgroup) {
                if (worker.isMemberofGroup(sendTo)) {
                    String outmessage = "message " + sendTo + ":" + login + " " + body + "\n";
                    worker.send(outmessage);
                }
            } else {
                if (sendTo.equalsIgnoreCase(worker.getLogin())) {
                    String outmessage = "message " + login + " " + body + "\n";
                    worker.send(outmessage);
                }
            }
        }
    }

    private void handleLogoff() throws IOException {
        server.removeWorker(this);
        List<ServerHandler> workerList = server.getHandlerList();

        String onlinemessage = "offline " + login + "\n";
        for(ServerHandler worker : workerList) {
            if (!login.equals(worker.getLogin())) {
                worker.send(onlinemessage);
            }
        }
        clientSocket.close();
    }

    public String getLogin() {
        return login;
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];

            Connection connection;
			try {
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
				        "root", "");
				PreparedStatement st = (PreparedStatement) connection
	                    .prepareStatement("Select id, password from approject where id=? and password=?");
	            st.setString(1, login);
	            st.setString(2, password);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next()) {
	            	 String message = "ok login\n";
	                 outputStream.write(message.getBytes());
	                 this.login = login;
	                 System.out.println("User logged in succesfully: " + login);
	                 LOGGER.info("User logged in successfully.");

	                 List<ServerHandler> workerList = server.getHandlerList();

	                 for(ServerHandler worker : workerList) {
	                     if (worker.getLogin() != null) {
	                         if (!login.equals(worker.getLogin())) {
	                             String message2 = "online " + worker.getLogin() + "\n";
	                             send(message2);
	                         }
	                     }
	                 }

	                 String onlinemessage = "online " + login + "\n";
	                 for(ServerHandler worker : workerList) {
	                     if (!login.equals(worker.getLogin())) {
	                         worker.send(onlinemessage);
	                     }
	                 }
	             } else {
	                 String message = "error login\n";
	                 outputStream.write(message.getBytes());
	                 System.err.println("Login failed for " + login);
	                 LOGGER.error("Login failed");
	             }
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

    private void send(String message) throws IOException {
        if (login != null) {
            try {
                outputStream.write(message.getBytes());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
