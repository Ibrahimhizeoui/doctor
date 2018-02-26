package com.ib92.doctor.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ib92.doctor.exception.UserNotFoundException;
import com.ib92.doctor.model.Consultation;
import com.ib92.doctor.model.User;
import com.ib92.doctor.repository.UserRepository;
import com.ib92.doctor.service.ConsultaionService;
import com.ib92.doctor.service.UserService;

@RestController
public class ConsultationController {

	@Autowired
	ConsultaionService consultationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	private User user;
	@RequestMapping(value="/consultation", method = RequestMethod.POST)
	public ResponseEntity<?> saveConsultation(@RequestBody Consultation consultation){
		this.consultationService.addConsultation(consultation);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("")
				.buildAndExpand().toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@RequestMapping(value="{id}/consultation", method = RequestMethod.GET)
	public List<Consultation> getListConsultationsByUser(@PathVariable int id){
		this.validateUser(id);
		return this.consultationService.findconsultations(id);
	}
	
	private void validateUser(int userId) {
		this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
