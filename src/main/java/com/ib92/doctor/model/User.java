package com.ib92.doctor.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	@OneToMany(mappedBy = "user")
	private List<Consultation> consultations;
	
	public User() {}
	
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public void setId(int id) {
		this.id = id;
	}
}
