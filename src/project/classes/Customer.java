package project.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approject")
public class Customer extends User{
	@Id
	String Cust_ID;

	public String getCust_ID() {
		return Cust_ID;
	}

	public void setCust_ID(String cust_ID) {
		Cust_ID = cust_ID;
	}
	
	

}
