package com.CollageAdmissionForm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CollageAdmissionForm.model.RegistrationDetails;

@Repository
public interface RegistrationDetailsRepo extends  JpaRepository<RegistrationDetails, Integer>{

}
