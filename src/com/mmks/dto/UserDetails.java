package com.mmks.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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

	// Saving collection of value objects
	@ElementCollection(fetch = FetchType.LAZY) // LAZY is default /EAGER
	@JoinTable(name = "USER_ADDRESSES", joinColumns = @JoinColumn(name = "USER_ID_FK"))
	// @GenericGenerator(name = "hilo-gen", strategy = "hilo") // hilo is no longer supported
	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name = "ADDRESS_KEY_COLUMN") }, generator = "sequence-gen", type = @Type(type = "long"))
	private Collection<Address> addresses = new ArrayList<Address>();

	
	// One-To-One mapping to Citizenship
	@OneToOne
	@JoinColumn(name="USER_CITIZENSHIP_FK")
	private Citizenship citizenship;

	
	// One-To-Many mapping to Citizenship
//	@OneToMany
//	@JoinTable(name="USERS_JOIN_VEHICLES" 
//			,joinColumns=@JoinColumn(name="USER_ID")
//			,inverseJoinColumns=@JoinColumn(name="USER_VEHICLE_ID"))
	
	@OneToMany(mappedBy="user")
	private Set<Vehicle> vehicles;	
	
	
	
	// ----------------------------------------------------------------------//

	public String getLong_description() {
		return long_description;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
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

	public Citizenship getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Citizenship citizenship) {
		this.citizenship = citizenship;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tuserId: " + userId + "\n\tuserName: " + userName + "\n\tdescription: "
				+ description + "\n\tlong_description: " + long_description + "\n\tsex: " + sex + "\n\tjoinedDate: "
				+ joinedDate + "\n\taddresses: " + Arrays.toString(addresses.toArray()) + "\n\tcitizenship: " + citizenship + "\n\tvehicles: "
				+ Arrays.toString(vehicles.toArray()) + "\n}";
	}

	

}
