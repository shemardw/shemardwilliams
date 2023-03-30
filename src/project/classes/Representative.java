package project.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approject")
public class Representative extends User {
	@Id
	String Rep_ID;

	public String getRep_ID() {
		return Rep_ID;
	}

	public void setRep_ID(String rep_ID) {
		Rep_ID = rep_ID;
	}
	
}
