package com.mmks.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userdtlid_generator")
	@SequenceGenerator(name = "userdtlid_generator", sequenceName = "userdtl_sequence")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_DESC")
	private String description;

	@Column(name = "USER_LONG_DESC")
	@Lob
	private String long_description;

	@Transient
	private String sex;

	@Column(name = "USER_JOINED_DATE")
	@Temporal(TemporalType.DATE)
	private Date joinedDate;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "street", column = @Column(name = "home_street")), 
		@AttributeOverride(name = "city", column = @Column(name = "home_town")),
		@AttributeOverride(name = "state", column = @Column(name = "home_state")), 
		@AttributeOverride(name = "pincode", column = @Column(name = "home_pincode")) 
	})
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "street", column = @Column(name = "work_street")), 
		@AttributeOverride(name = "city", column = @Column(name = "work_town")),
		@AttributeOverride(name = "state", column = @Column(name = "work_state")), 
		@AttributeOverride(name = "pincode", column = @Column(name = "work_pincode")) 
	})
	private Address workAddress;

	// ----------------------------------------------------------------------//
	public String getLong_description() {
		return long_description;
	}

	public void setLong_description(String long_description) {
		this.long_description = long_description;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName.toUpperCase();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	@Override
	public String toString() {
		return "UserDetails [\nuserId=" + userId + ", \nuserName=" + userName + ", \ndescription=" + description
				+ ", \nlong_description=" + long_description + ", \nsex=" + sex + ", \njoinedDate=" + joinedDate
				+ "\nHomeAddress " + ((homeAddress != null) ? homeAddress.toString() : "[]") + "\nWorkAddress "
				+ ((workAddress != null) ? workAddress.toString() : "[]") + "]";
	}

}
