package project.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.authentication.Login;
import project.authentication.Registration;
import project.client.ChatLogin;
import project.server.ServerDriver;

import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CustomerDashboard extends JFrame{
	private static final Logger LOGGER = LogManager.getLogger(CustomerDashboard.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDashboard window = new CustomerDashboard();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(650, 600));
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
					.addGap(10))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
		);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setLayout(null);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(420, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 10, 271, 43);
		panel.add(lblNewLabel);
		
		JButton btnComplaint = new JButton("Complaint");
		btnComplaint.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		btnComplaint.setIconTextGap(35);
		btnComplaint.setHorizontalTextPosition(SwingConstants.LEADING);
		btnComplaint.setForeground(Color.WHITE);
		btnComplaint.setBackground(Color.RED);
		btnComplaint.setBounds(10, 77, 271, 43);
		btnComplaint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComplaintForm complaint = null;
				try {
					complaint = new ComplaintForm();
					LOGGER.info("Complain made successfully.");
				} catch (SQLException e1) {
					e1.printStackTrace();
					LOGGER.error("Error occurred during complaint processing");
				}
				complaint.setVisible(true);				
			}
		});
		panel.add(btnComplaint);
		
		JButton btnQuery = new JButton("Query");
		btnQuery.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnQuery.setIconTextGap(35);
		btnQuery.setHorizontalTextPosition(SwingConstants.LEADING);
		btnQuery.setForeground(Color.WHITE);
		btnQuery.setBackground(Color.RED);
		btnQuery.setBounds(10, 194, 271, 43);
		btnQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerDriver serverDriver = new ServerDriver();
				String[] arguments = new String[] {"123"};
				serverDriver.main(arguments);
				ChatLogin chatLogin = new ChatLogin();
				chatLogin.setVisible(true);	
				LOGGER.info("Logged in successfully to chat from Customer dashboard.");
			}
		});
		panel.add(btnQuery);
		
		JButton btnRequestService = new JButton("Request Service\r\n");
		btnRequestService.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		btnRequestService.setIconTextGap(35);
		btnRequestService.setHorizontalTextPosition(SwingConstants.LEADING);
		btnRequestService.setForeground(Color.WHITE);
		btnRequestService.setBackground(Color.RED);
		btnRequestService.setBounds(10, 306, 271, 43);
		btnRequestService.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnRequestService, "Sorry, service currently out of order.");
				
			}
		});
		panel.add(btnRequestService);
		
		
		JButton btnLogOff = new JButton("Logout");
		btnLogOff.setIconTextGap(35);
		btnLogOff.setHorizontalTextPosition(SwingConstants.LEADING);
		btnLogOff.setForeground(Color.WHITE);
		btnLogOff.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		btnLogOff.setBackground(Color.RED);
		btnLogOff.setBounds(10, 422, 271, 43);
		btnLogOff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(btnLogOff, "Are you sure?");
                if (answer == JOptionPane.YES_OPTION) {
                    dispose();
                    LOGGER.info("Logged off successfully.");
                    Login login = new Login();
                    //login.setTitle("");
                    login.setVisible(true);
                }
                dispose();
                Login login = new Login();

                login.setTitle("Login");
                login.setVisible(true);
				
			}
		});
		panel.add(btnLogOff);
		getContentPane().setLayout(groupLayout);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
}
