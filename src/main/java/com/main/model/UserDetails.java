package com.main.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserDetails {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="User_Id")
	private Long userId;
	
	@NotNull
	@Size(min=3,max=40)
	@Column(name="First_Name")
	private String firstName;
	
	@NotNull
	@Size(min=3,max=40)
	@Column(name="Last_Name")
	private String lastName;

	@NotNull
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="Date_Of_Birth")
	private Date dateOfBirth;
	
	@NotNull
	@Size(max=50)
	@Column(unique=true, name="Email_Id")
	private String emailId;
	
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", emailId=" + emailId + ", password=" + password + ", gender="
				+ gender + ", contactNo=" + contactNo + ", language=" + language + ", role=" + role + ", addresses="
				+ addresses + "]";
	}

	@NotNull
	@Size(min=8, max=20)
	@Column(name="Password")
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")
	private Gender gender;
	
	@NotNull
	@Size(min=13,max=13)
	@Column(name="Contact_Number")
	private String contactNo;
	
	@NotNull
	@Column(name="Language")
	private String language;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Role")
	private Role role = Role.User;
	
	@JsonIgnore
	@OneToMany(mappedBy="userDetails", fetch=FetchType.EAGER,  targetEntity=Address.class)
	@Cascade(CascadeType.ALL)
	private List<Address> addresses;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	
}