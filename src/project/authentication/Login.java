package project.authentication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.form.CustomerDashboard;
import project.form.RepresentativeDashboard;
import project.form.TechnicianDashboard;




public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel label;
    private JPanel contentPane;
    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    
	public static void main(String[] args) {
		
		 EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("Something went Wrong.");
                }
				
			}
		});

	}
	
	public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        loginLabel.setBounds(423, 13, 273, 93);
        contentPane.add(loginLabel);

        userIDField = new JTextField();
        userIDField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        userIDField.setBounds(481, 170, 281, 68);
        contentPane.add(userIDField);
        userIDField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel userIDLabel= new JLabel("UserID");
        userIDLabel.setBackground(Color.BLACK);
        userIDLabel.setForeground(Color.BLACK);
        userIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        userIDLabel.setBounds(250, 166, 193, 52);
        contentPane.add(userIDLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBackground(Color.CYAN);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        passwordLabel.setBounds(250, 286, 193, 52);
        contentPane.add(passwordLabel);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        loginButton.setBounds(545, 392, 162, 73);
        loginButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIDField.getText();
                String password = passwordField.getText();
                String type = "";
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", ""); 
                    
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", "");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select id, password from approject where id=? and password=?");
                    
                    String query = "SELECT type FROM approject WHERE id='" + userID + "'";
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next()) {
                    	type = resultSet.getString(1);
                    }

                    st.setString(1, userID);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        if(type.equalsIgnoreCase("Customer")) {
                        	CustomerDashboard customerDashboard = new CustomerDashboard();  
                        	customerDashboard.setVisible(true);
                        }else if(type.equalsIgnoreCase("Technician")) {
                        	TechnicianDashboard technicianDashboard = new TechnicianDashboard();
                        	technicianDashboard.setVisible(true);
                        }else if(type.equalsIgnoreCase("Representative")){
                        	RepresentativeDashboard representativeDashboard = new RepresentativeDashboard();
                        	representativeDashboard.setVisible(true);
                        }
                        JOptionPane.showMessageDialog(loginButton, "You have successfully logged in");
                        LOGGER.info("Login Successful");
                    } else {
                        JOptionPane.showMessageDialog(loginButton, "Wrong UserID & Password");
                        LOGGER.warn("Incorrect Credentials. Check UserID or Password");
                    }
                    st.close();
                    con.close();
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    LOGGER.error("Something went Wrong.");
                }
            }
        });

        contentPane.add(loginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }

}
