package project.client;

public interface UserStatusListener {
	public void online(String userName);
	public void offline(String userName);
}
