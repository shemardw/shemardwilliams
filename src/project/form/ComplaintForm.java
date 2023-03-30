package project.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.logging.log4j.*;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import project.classes.Complaint;
import javax.swing.JButton;

public class ComplaintForm extends JFrame{
	private static final Logger LOGGER = LogManager.getLogger(ComplaintForm.class);
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComplaintForm window = new ComplaintForm();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public ComplaintForm() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	private void initialize() throws SQLException {
		setBounds(100, 100, 1047, 504);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTextArea txtrJndjwnjadnw = new JTextArea();
		txtrJndjwnjadnw.setText("\r\n");
		txtrJndjwnjadnw.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		String complaints [] = {"Broadband", "Telephone", "Cable"};
		JComboBox <String> comboBox = new JComboBox<>(complaints);
		comboBox.setSelectedIndex(0);
		
		JLabel lblNewLabel = new JLabel("Type of Complaint");
		lblNewLabel.setLabelFor(comboBox);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		
		JLabel lblDetailsOfComplaint = new JLabel("Details of Complaint: Be as concise as possible.");
		lblDetailsOfComplaint.setLabelFor(txtrJndjwnjadnw);
		lblDetailsOfComplaint.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		
		JLabel lblProposedDateOf = new JLabel("Proposed Date of Visit");
		lblProposedDateOf.setLabelFor(dateChooser);
		lblProposedDateOf.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setLabelFor(textField);
		lblEmailAddress.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		
		JLabel lblMobileNumber = new JLabel("Contact Number");
		lblMobileNumber.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		
		textField_1 = new JTextField();
		lblMobileNumber.setLabelFor(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtrJndjwnjadnw, GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmailAddress, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, 0, 255, Short.MAX_VALUE)))
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMobileNumber, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblProposedDateOf, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDetailsOfComplaint, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(407)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmailAddress, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addComponent(lblDetailsOfComplaint, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(53)
									.addComponent(lblProposedDateOf, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(61)
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMobileNumber, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrJndjwnjadnw, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
					.addContainerGap())
		);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Complaint customerComplaint = new Complaint(comboBox.getSelectedItem().toString(), dateChooser.getDate().toString() , textField.getText(), textField_1.getText(), txtrJndjwnjadnw.getText());
				if(customerComplaint.sendToDatabase()) {
					JOptionPane.showMessageDialog(btnNewButton, "Complaint successfully made. We will respond as soon as possible.");
					LOGGER.info("Complaint made successfully.");
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Error encountered, try again.");
					LOGGER.info("Complaint made unsuccessfully.");
				}
				dateChooser.setDate(null);
				textField.setText(null);
				textField_1.setText(null);
				txtrJndjwnjadnw.setText(null);
			}
		});
		getContentPane().setLayout(groupLayout);
	}
}