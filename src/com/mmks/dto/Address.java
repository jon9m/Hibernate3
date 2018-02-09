package com.mmks.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name = "ADDRESS_STREET")
	private String street;
	
	@Column(name = "ADDRESS_CITY")
	private String city;
	
	@Column(name = "ADDRESS_STATE")
	private String state;
	
	@Column(name = "ADDRESS_PINCODE")
	private String pincode;

	public Address() {

	}

	public Address(String street, String city, String state, String pincode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [\nstreet=" + street + ", \ncity=" + city + ", \nstate=" + state
				+ ", \npincode=" + pincode + "]";
	}

}
