package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer requestid;
	
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	
	
	private String comments;

	

	public Integer getRequestid() {
		return requestid;
	}


	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "Contact [requestid=" + requestid + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", comments=" + comments + "]";
	}
	
	
	
}