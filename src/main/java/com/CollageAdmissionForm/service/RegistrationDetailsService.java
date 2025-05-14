package com.CollageAdmissionForm.service;

import java.util.List;

import com.CollageAdmissionForm.model.RegistrationDetails;

public interface RegistrationDetailsService {

	RegistrationDetails saveAdmission(RegistrationDetails details);

	List<RegistrationDetails> getAllRegistration();

	RegistrationDetails getRegiDetailById(Integer registrationId);

	//void deleteRegistrationDetails(RegistrationDetails deleteDetails);

}
