package com.ib92.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;
import com.ib92.doctor.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findUser(int id){
		return this.userRepository.findOne(id);
	}
	
	
}
