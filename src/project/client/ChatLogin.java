package project.client;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ChatLogin extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(ChatLogin.class);
    private final Client client;
    JTextField userIdField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public ChatLogin() {
        super("Login");

        this.client = new Client("localhost", 8888);
        client.connect();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(userIdField);
        panel.add(passwordField);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }

    private void performLogin() {  
        loginButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String userId = userIdField.getText();
                String password = passwordField.getText(); 
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", ""); 

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select id, password from approject where id=? and password=?");

                    st.setString(1, userId);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        if (client.login(userId, password)) {
                        	
                            UserListPane userListPane = new UserListPane(client);
                            JFrame frame = new JFrame("User List");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(400, 600);

                            frame.getContentPane().add(userListPane, BorderLayout.CENTER);
                            frame.setVisible(true);

                            setVisible(false);
                            LOGGER.info("Logged in successfully to chat.");
                        } else {
                            JOptionPane.showMessageDialog(loginButton, "Invalid username or password.");
                            LOGGER.info("Logged in unsuccessfully to chat.");
                        }
                    }
                }catch (SQLException | HeadlessException | IOException se) {
					se.printStackTrace();
					LOGGER.error("Something went Wrong.");
					
				}
        	}
        });
    }

    public static void main(String[] args) {
        ChatLogin chatLogin = new ChatLogin();
        chatLogin.setVisible(true);
    }
}
