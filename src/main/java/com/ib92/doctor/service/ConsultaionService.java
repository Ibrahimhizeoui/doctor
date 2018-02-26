package com.ib92.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;
import com.ib92.doctor.repository.ConsultationRepository;

@Service
public class ConsultaionService {
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	public Consultation addConsultation(Consultation consultation){
		return consultationRepository.save(consultation);
	}
	
	public List<Consultation> findconsultations(int id){
		return this.consultationRepository.findByUserId(id);
	}
	
	
}
