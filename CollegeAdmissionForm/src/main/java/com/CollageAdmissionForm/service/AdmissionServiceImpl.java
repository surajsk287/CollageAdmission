package com.CollageAdmissionForm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CollageAdmissionForm.model.RegistrationDetails;
import com.CollageAdmissionForm.repo.AdmissionRepo;
@Service
public class AdmissionServiceImpl implements AdmissionService {
	@Autowired
	AdmissionRepo admissionRepo;

	@Override
	public void saveData(RegistrationDetails details) {
		admissionRepo.save(details);
		
	}

}
