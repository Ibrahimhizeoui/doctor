package com.ib92.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Integer>{
	List<Consultation> findByUserId(int id);
}
