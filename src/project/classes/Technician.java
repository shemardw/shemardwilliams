package project.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approject")
public class Technician extends User{
	@Id
	String Tech_ID;

	public String getTech_ID() {
		return Tech_ID;
	}

	public void setTech_ID(String tech_ID) {
		Tech_ID = tech_ID;
	}
	

}
