package project.classes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "approject")
public class User {

	String Firstname;
	String Lastname;
	String password;
	String type;
	
	
	
	public User() {
		Firstname="";
		Lastname="";
		password="";
		type="";
	}
	
	public User(String fname, String lname, String password, String type) {
		Firstname=fname;
		Lastname=lname;
		this.password=password;
		this.type=type;
	}
	
	public User(User U) {
		this.Firstname=U.Firstname;
		this.Lastname=U.Lastname;
		this.password=U.password;
		this.type=U.type;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [Firstname=" + Firstname + ", Lastname=" + Lastname + ", password=" + password + ", type=" + type
				+ "]";
	}
	
}
