package project.authentication;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FirstLoginScreen {
	
	
	public static void main(String [] args) {
		
		 JFrame homepage = new JFrame("Home Page");
		 
		 JButton signButton  = new JButton("Sign In");
		 signButton.setBounds(290,200,450,100);
		 JButton registerButton= new JButton("Register");
		 registerButton.setBounds(290,400,450,100);
		 
		 
		 
		 registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			homepage.dispose();	
			Registration r= new Registration();
			r.setVisible(true);
						
			}
		});
		 
		 signButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			homepage.dispose();	
			Login login = new Login();
			login.setVisible(true);
			
			}
		});
		 
		 homepage.add(signButton);
		 homepage.add(registerButton);
		 homepage.getContentPane().setLayout(new GridLayout(12, 12));
	     homepage.setLayout(null);
	     homepage.setVisible(true);
	     homepage.setBounds(450, 190, 1014, 650);
	     homepage.setResizable(false);
	     homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
}
