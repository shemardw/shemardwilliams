package project.authentication;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Registration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userIDField;
	private JPasswordField passwordField;
	private JButton registrationButton;
	private JRadioButton customerRadioButton;
	private JRadioButton technicianRadioButton;
	private JRadioButton representativeRadioButton;
	private static final Logger LOGGER = LogManager.getLogger(Registration.class);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			
			@Override
			public void run() {
				try {
                    Registration frame = new Registration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("Something went Wrong.");
                }
				
			}
		});
	}
	
	public Registration() {
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 650);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
 
        JLabel pageLabel = new JLabel("New User Register"); 
        pageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        pageLabel.setBounds(362, 52, 325, 50);
        contentPane.add(pageLabel);

        customerRadioButton = new JRadioButton("Customer");
        customerRadioButton.setBounds(100, 102, 100, 30);
        technicianRadioButton = new JRadioButton("Technician");
        technicianRadioButton.setBounds(100,152,100,30);
        representativeRadioButton = new JRadioButton("Representative");
        representativeRadioButton.setBounds(100,202,120,30); 
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(customerRadioButton);
        buttonGroup.add(representativeRadioButton);
        buttonGroup.add(technicianRadioButton);
        
        contentPane.add(customerRadioButton);
        contentPane.add(representativeRadioButton);
        contentPane.add(technicianRadioButton);
        
        JLabel firstNameLabel = new JLabel("First name");
        firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstNameLabel.setBounds(58, 252, 99, 43);
        contentPane.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last name");
        lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lastNameLabel.setBounds(58, 343, 110, 29);
        contentPane.add(lastNameLabel);

        firstNameField = new JTextField();
        firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstNameField.setBounds(214, 252, 228, 50);
        contentPane.add(firstNameField);
        firstNameField.setColumns(10);

        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastNameField.setBounds(214, 343, 228, 50);
        contentPane.add(lastNameField);
        lastNameField.setColumns(10);



        userIDField = new JTextField();
        userIDField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        userIDField.setBounds(707, 240, 228, 50);
        contentPane.add(userIDField);
        userIDField.setColumns(10);

        JLabel userID = new JLabel("User ID");
        userID.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userID.setBounds(542, 252, 99, 29);
        contentPane.add(userID);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordLabel.setBounds(542, 343, 99, 24);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 330, 228, 50);
        contentPane.add(passwordField);

        registrationButton = new JButton("Register");
        
        registrationButton.addActionListener(new ActionListener() {
        	
        	@Override
        	
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String userID = userIDField.getText();
                String password = passwordField.getText();
                String type = " ";
                
                String msg = "" + firstName;
                msg += " \n";
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                    
                    if(customerRadioButton.isSelected()) {
                    	type="Customer";
                    }else if(technicianRadioButton.isSelected()) {
                    	type="Technician";
                    }else {
						type="Representative";
					}
                    String query = "INSERT INTO approject values('" + userID + "','" + type + "','" + firstName + "','" + lastName + "','" +
                        password + "')"; 

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(registrationButton, "This User alredy exists");
                        LOGGER.warn("Duplicate Users are not allowed.");
                    } else {
                        JOptionPane.showMessageDialog(registrationButton,
                            "Welcome, " + msg + "Your account is sucessfully created.");
                        firstNameField.setText(null);
                        lastNameField.setText(null);
                        userIDField.setText(null);
                        passwordField.setText(null);
                        LOGGER.info("Account Creation Successful.");
                    }
                    dispose();
                    Login login = new Login();
        			login.setVisible(true);	
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    LOGGER.error("Something Went Wrong During Account Creation");
                }
            }

        });
        registrationButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        registrationButton.setBounds(399, 520, 259, 74);
        contentPane.add(registrationButton);
        
	}
	
}
