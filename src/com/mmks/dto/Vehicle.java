package com.mmks.dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "USER_VEHICLE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR_TYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="CAR")
public class Vehicle {

	@Id
	@Column(name = "VEHICLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "VEHICLE_NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name="VEHICLE_USER_ID_FK")
	@NotFound(action=NotFoundAction.EXCEPTION)
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
