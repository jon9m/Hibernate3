package com.mmks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_VEHICLE")
public class Vehicle {

	@Id
	@Column(name = "VEHICLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "VEHICLE_NAME")
	private String name;

	@ManyToOne
	private UserDetails user;

	public Vehicle() {

	}

	public Vehicle(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tid: " + id + "\n\tname: " + name + "\n}";
	}

}
