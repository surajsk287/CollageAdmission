package com.CollageAdmissionForm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CollageAdmissionForm.model.CollegeMaster;
@Repository
public interface CollegeMasterRepo extends JpaRepository<CollegeMaster, Integer> {

}
