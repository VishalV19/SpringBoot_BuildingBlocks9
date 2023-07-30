package com.springboot_buildingblocks.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Entity
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
			
	@Column(name = "FRISTNAME", length=50, nullable=false)
	private String firstname;
	
	@Column(name = "LASTNAME", length=50, nullable=false)
	private String lastname;
	
	@Column(name = "EMAIL", length=50, nullable=false)
	private String email;
	
	@Column(name = "ROLE", length=50, nullable=false)
	private String role;
	
	@Column(name = "SSN",length=50, nullable=false, unique=false)
	private String ssn;
	
	@Column(name = "USERNAME",length=50, nullable=false, unique=true)
	private String username;

	//No args constructor
	public User() {
		
		// TODO Auto-generated constructor stub
	}
	
	//field constructor
	public User(Long id, String username, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.username=username;
	}

	// Getter & Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	// To String Method
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", role="
				+ role + ", ssn=" + ssn + ", username=" + username + "]";
	}
	
	
	
}
	
