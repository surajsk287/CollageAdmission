package com.CollageAdmissionForm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CollageAdmissionForm.model.RegistrationDetails;
import com.CollageAdmissionForm.repo.RegistrationDetailsRepo;

@Service
public class RegistrationDetailsServiceImpl implements RegistrationDetailsService {

	@Autowired
	RegistrationDetailsRepo registrationDetailsRepo;
	
	@Override
	public RegistrationDetails saveAdmission(RegistrationDetails details) {
		
		return registrationDetailsRepo.save(details);
	}

	@Override
	public List<RegistrationDetails> getAllRegistration() {
		
		return registrationDetailsRepo.findAll();
	}

	@Override
	public RegistrationDetails getRegiDetailById(Integer registrationId) {
		
		RegistrationDetails regi=registrationDetailsRepo.findById(registrationId).get();
		registrationDetailsRepo.delete(regi);
		return regi;
	}

//	@Override
//	public void deleteRegistrationDetails(RegistrationDetails deleteDetails) {
//		registrationDetailsRepo.delete(deleteDetails);
//		
//	}

}
