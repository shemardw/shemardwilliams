package project.client;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

public class UserListPane extends JPanel implements UserStatusListener {

	private static final long serialVersionUID = 1L;
	private final Client client;
	private JList<String> userListUI;
	private DefaultListModel<String> userListModel;

	public UserListPane(Client client) {
		this.client = client;
		this.client.addUserStatusListener(this);
		
		userListModel = new DefaultListModel<String>();
		userListUI = new JList<String>(userListModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(userListUI), BorderLayout.CENTER);
		
		userListUI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				if(e.getClickCount() > 1) {
					String user = userListUI.getSelectedValue();
					MessagePane messagePane = new MessagePane(client, user);
					
					JFrame frame = new JFrame("Message: " + user);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setSize(500,500);
					frame.getContentPane().add(messagePane, BorderLayout.CENTER);
					frame.setVisible(true);
				}
			}
		});
	}

	public static void main(String[] args) {
		Client client = new Client("localhost", 8888);
		
		UserListPane userListPane = new UserListPane(client);
		JFrame frame = new JFrame("User List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		
		frame.getContentPane().add(userListPane, BorderLayout.CENTER);
		frame.setVisible(true);
		
		if(client.connect()) {
			try {
				client.login("g","g");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void online(String user) {
		userListModel.addElement(user);
		
	}

	@Override
	public void offline(String user) {
		userListModel.removeElement(user);
		
	}
}
