package project.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MessagePane extends JPanel implements MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Client client;
	private final String user;
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> messageList = new JList<String>(listModel);
	private JTextField chatInputBox = new JTextField();
	
	public MessagePane(Client client, String user) {
		this.client = client;
		this.user = user;
		
		client.addMessageListener(this);
		
		setLayout(new BorderLayout());
		add(new JScrollPane (messageList), BorderLayout.CENTER);
		add(chatInputBox, BorderLayout.SOUTH);
		
		chatInputBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String text = chatInputBox.getText();
					client.message(user, text);
					listModel.addElement("You: " + text);
					chatInputBox.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}); 
	}

	@Override
	public void getMessage(String sender, String messageBody) {
		if(user.equalsIgnoreCase(sender)) {
			String line = sender + ": " + messageBody;
			listModel.addElement(line);
		}			
	}

}
