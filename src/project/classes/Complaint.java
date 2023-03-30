package project.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

@Table(name="complaint")
@Entity
public class Complaint {
	@Id
	private int complaintId;
	
	private String category;
	private String date;
	private String email;
	private String contactNumber;
	private String details;
	private String status;
	private String technicianId;
	private String response;
	
	public Complaint() {
		super();
	}
	public Complaint(String category, String date, String email,String contactNumber, String details) {
		this.category = category;
		this.date = date;
		this.email = email;
		this.contactNumber = contactNumber;
		this.details = details;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public String getCategory() {
		return category;
	}
	public String getStatus() {
		return status;
	}
	public String getTechnicianId() {
		return technicianId;
	}
	public String getDate() {
		return date;
	}
	public String getEmail() {
		return email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getDetails() {
		return details;
	}
	public String getResponse() {
		return response;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", category=" + category + ", status=" + status
				+ ", technicianId=" + technicianId + ", date=" + date + ", email=" + email + ", contactNumber="
				+ contactNumber + ", details=" + details + ", response=" + response + "]";
	}
	
	public boolean sendToDatabase() {
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			        "root", "");
			String sql = "INSERT INTO complaint (category, date, email, contactNumber, details) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, this.getCategory());
			preparedStatement.setString(2, this.getDate());
			preparedStatement.setString(3, this.getEmail());
			preparedStatement.setString(4, this.getContactNumber());
			preparedStatement.setString(5, this.getDetails());
			
			preparedStatement.execute();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}	
}
