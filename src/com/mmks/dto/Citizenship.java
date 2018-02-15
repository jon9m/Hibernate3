package com.mmks.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_CITIZENSHIP")
public class Citizenship {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private int years;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	public Citizenship() {

	}

	public Citizenship(String name, int years, Date startDate) {
		super();
		this.name = name;
		this.years = years;
		this.startDate = startDate;
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

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tid: " + id + "\n\tname: " + name + "\n\tyears: " + years
				+ "\n\tstartDate: " + startDate + "\n}";
	}

}
