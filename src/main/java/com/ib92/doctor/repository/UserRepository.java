package com.ib92.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findById(int id);
	}
