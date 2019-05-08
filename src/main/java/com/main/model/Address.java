package com.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kandarp Dave
 * 
 * The Entity class Address
 */
@Entity
public class Address {
	
	/** Address Id */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Address_Id")
	private Long addrId;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private UserDetails userDetails;

	/** Street 1 */
	@NotNull
	@Size(max=80)
	@Column(name="Street_1")
	private String street1;
	
	/** Street 2 */
	@NotNull
	@Size(max=80)
	@Column(name="Street_2")
	private String street2;
	
	/** Pincode */
	@NotNull
	@Column(name="Pincode")
	private Integer pincode;
	
	/** City */
	@NotNull
	@Size(max=50)
	@Column(name="City")
	private String city;
	
	/** State */
	@NotNull
	@Size(max=50)
	@Column(name="State")
	private String state;
	
	/** Country */
	@NotNull
	@Size(max=50)
	@Column(name="Country")
	private String country;

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}	
}
