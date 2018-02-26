package com.ib92.doctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="consultations")
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstAnswer;
	private String secondAnswer;
	private String thirdAnswer;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	public Consultation() {}

	public Consultation(String firstAnswer, String secondAnswer, String thirdAnswer) {
		this.firstAnswer = firstAnswer;
		this.secondAnswer = secondAnswer;
		this.thirdAnswer = thirdAnswer;
	}

	public String getFirstAnswer() {
		return firstAnswer;
	}

	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}

	public String getSecondAnswer() {
		return secondAnswer;
	}

	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}

	public String getThirdAnswer() {
		return thirdAnswer;
	}

	public void setThirdAnswer(String thirdAnswer) {
		this.thirdAnswer = thirdAnswer;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
