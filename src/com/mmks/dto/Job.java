package com.mmks.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USER_JOB")
public class Job {

	@Id
	@Column(name = "JOB_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "JOB_NAME")
	private String jobName;

	@ManyToMany(mappedBy="jobs")
	private Set<UserDetails> users = new HashSet<UserDetails>();

	public Job() {

	}

	public Job(String jobName) {
		super();
		this.jobName = jobName;
	}
	
	public Job(String jobName, int id) {
		super();
		this.id = id;
		this.jobName = jobName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Set<UserDetails> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDetails> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tid: " + id + "\n\tjobName: " + jobName + "\n}";
	}

}
